var AdminPageContainer = React.createClass( {

    getInitialState: function() {
        return {
            counties: [],
            parties: [],
            multiPartyListFile: null,
            singlePartyListFile: null,

            party: {
                partyId: "",
                partyName: ""
            },
            county: {
                countyName: ""
            },
            partyIdToDelete: "",
            partyIdForCandidateList: "",
            countyIdForCandidateList: "",
            countyListIdToDelete: "",
            candidates: [],

        };
    },

    handleCountyDeleteList: function( county ) {
        for ( var i = 0; i < this.state.candidates.length; i++ ) {
            if ( this.state.candidates[i].countyId == county.countyId ) {
                return true;
            }
        }
    },

    handleCountyList: function( county ) {
        var count = 0;
        for ( var i = 0; i < this.state.candidates.length; i++ ) {
            if ( this.state.candidates[i].countyId == county.countyId ) { ++count; }
        }
        if ( count > 0 ) {
            return false
        } else {
            return true
        }

    },
    
    handlePartyList: function( party ) {
        var count = 0;
        for ( var i = 0; i < this.state.candidates.length; i++ ) {
            if ( this.state.candidates[i].partyId == party.partyId ) { ++count; }
        }
        if ( count > 0 ) {
            return false
        } else {
            return true
        }

    },
    
    

    componentWillMount: function() {
        var _this = this;
        axios.get( '/api/party' ).then( function( response ) {
            _this.setState( { parties: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

        axios.get( '/api/candidate' ).then( function( response ) {
            _this.setState( { candidates: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

        axios.get( '/api/county' ).then( function( response ) {
            _this.setState( { counties: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

    },


    handleOnSubmitAddCounty: function( e ) {
        e.preventDefault();
        var _this = this;



        axios.post( '/api/county', this.state.county ).then( function( response ) {
            console.log( 'county ' + _this.state.county['countyName'] + ' added' );

            document.getElementById( "countyAddFormMsg" ).innerHTML = `<div class="alert alert-success">
  <strong>${_this.state.county.countyName}</strong> - apygarda sukurta.
</div>`;
            _this.setState( {
                county: {
                    countyName: ""
                }
            });
            setTimeout( function() {
                document.getElementById( "countyAddFormMsg" ).innerHTML = "";
            }, 7000 );

            axios.get( '/api/county' ).then( function( response ) {
                _this.setState( { counties: response.data });
            }).catch( function( error ) {
                console.log( error );
            });
        }).catch( function( error ) {
            console.log( error );
            document.getElementById( "countyAddFormMsg" ).innerHTML = `<div class="alert alert-danger">
  <strong>Tokia apygarda jau yra!!</strong></div>`;
            setTimeout( function() {
                document.getElementById( "countyAddFormMsg" ).innerHTML = "";
            }, 8000 );
        });

        document.getElementById( "countyAddForm" ).reset();
    },

    handleOnDeleteCounty: function( county ) {
        var _this = this;
        return function() {
            axios.delete( '/api/county/' + county ).then( function( response ) {
                console.log( 'county ' + county.countyName + ' deleted' );

                axios.get( '/api/county' ).then( function( response ) {
                    _this.setState( { counties: response.data });
                });
            });
        };

    },

    handleOnSubmitAddParty: function( e ) {
        e.preventDefault();
        var _this = this;
        axios.post( '/api/party', _this.state.party ).then( function( response ) {
            console.log( 'party ' + _this.state.party['partyName'] + ' added' );
            document.getElementById( "partyAddFormMsg" ).innerHTML = `<div class="alert alert-success"><strong>` + `Partija<span> ${_this.state.party['partyName']}</span> sėkmingai sukurta!` + `</strong></div>`;
            _this.setState( {
                party: {
                    partyId: "",
                    partyName: ""
                }
            });
            setTimeout( function() {
                document.getElementById( "partyAddFormMsg" ).innerHTML = "";
            }, 4500 );
            axios.get( '/api/party' ).then( function( response ) {
                _this.setState( { parties: response.data });
            });
        }).catch( function( error ) {
            console.log( error );
            document.getElementById( "partyAddFormMsg" ).innerHTML = `<div class="alert alert-danger"><strong">Partija tokiu pavadinimu/numeriu jau įvesta!</strong></div>`;
            _this.setState( {
                party: {
                    partyId: "",
                    partyName: ""
                }
            });
            setTimeout( function() {
                document.getElementById( "partyAddFormMsg" ).innerHTML = "";
            }, 4500 );
        });

        document.getElementById( "partyAddForm" ).reset();
    },

    handleOnChangeInputPartyData: function( field ) {
        var _this = this;
        return function( e ) {
            var party = _this.state.party;
            party[field] = e.target.value;
            _this.setState( { party: party });
        };
    },

    handleOnChangeInputCountyData: function( field ) {
        var _this = this;
        return function( e ) {
            var county = _this.state.county;
            county[field] = e.target.value;
            _this.setState( { county: county });
        };
    },

    handleOnChangeInputPartyIdToDelete: function( el ) {
        this.setState( { partyIdToDelete: el.target.value });
    },

    // Kazkodel veikia tik @PostMapping kontroleris back-end'e
    handleOnSubmitDeleteParty: function( e ) {
        e.preventDefault();
        var _this = this;
        axios.post( '/api/party/' + _this.state.partyIdToDelete ).then( function( response ) {
            console.log( 'party who\'s ID:' + _this.state.partyIdToDelete + ' deleted' );


            axios.get( '/api/party' ).then( function( response ) {
                _this.setState( { parties: response.data });
            });

            _this.setState( { partyIdToDelete: "" });

            document.getElementById( "partyDeletionMsg" ).innerHTML = `<div class="alert alert-success">
             <strong>Partija ištrinta</strong>
             </div>`;
            setTimeout( function() {
                document.getElementById( "partyDeletionMsg" ).innerHTML = "";
            }, 4500 );
        });

        axios.post( '/api/candidate/party/' + _this.state.partyIdToDelete ).then( function( response ) {
            console.log( `candidate list of party who\'s ID:  ${_this.state.partyIdToDelete} deleted` );
        }).catch( function( error ) {
            console.log( error );
            document.getElementById( "partyDeletionMsg" ).innerHTML = `<div class="alert alert-danger"><strong">Pasirinkite partiją!</strong></div>`;
            _this.setState( {
                party: {
                    partyId: "",
                    partyName: ""
                }
            });
            setTimeout( function() {
                document.getElementById( "partyDeletionMsg" ).innerHTML = "";
            }, 4500 );
        });

        document.getElementById( "partyCandidateListDeleteForm" ).reset();

    },

    handleOnChangeSelectPartyIdForCandidateList: function( e ) {
        console.log( e.target.value );
        this.setState( { partyIdForCandidateList: e.target.value });
    },

    handleOnChangeMultiPartyListFileInput: function( file ) {
        this.setState( { multiPartyListFile: file });
    },

    handleOnSubmitMultiPartyList: function( e ) {
        e.preventDefault();
        console.log( this.state.multiPartyListFile );
        var _this = this;
        var data = new FormData();
        var config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        data.append( 'file', _this.state.multiPartyListFile );
        data.append( 'partyId', _this.state.partyIdForCandidateList );
        axios.post( '/api/candidate/multiPartyListUpload', data, config ).then( function( response ) {
            console.log( "multi party list added" );
            document.getElementById( "partyListAddFormMsg" ).innerHTML = `<div class="alert alert-success">
             <strong>Partijos sąrasas sėkmingai užregistruotas</strong>
             </div>`;
         
            setTimeout( function() {
                document.getElementById( "partyListAddFormMsg" ).innerHTML = "";
            }, 4500 );
            
            axios.get( '/api/party' ).then( function( response ) {
                _this.setState( { parties: response.data });
            }).catch( function( error ) {
                console.log( error );
            });

            axios.get( '/api/candidate' ).then( function( response ) {
                _this.setState( { candidates: response.data });
            }).catch( function( error ) {
                console.log( error );
            });
            _this.setState( { partyIdForCandidateList: "", multiPartyListFile: null });
        }).catch( function( error ) {
            document.getElementById( "partyListAddFormMsg" ).innerHTML = `<div class="alert alert-danger">
             <strong>Klaida, nepasirinkta partija arba kandidatų sąrašas.</strong>
             </div>`;
            setTimeout( function() {
                document.getElementById( "partyListAddFormMsg" ).innerHTML = "";
            }, 4500 );
            console.error( error );

        });

        document.getElementById( "partyCandidateListAddForm" ).reset();
    },

    //Vienmandatis kandidatu sarasas
    handleOnChangeSelectCountyIdForCandidateList: function( e ) {
        console.log( e.target.value );


        this.setState( { countyIdForCandidateList: e.target.value });
    },

    handleOnChangeSinglePartyListFileInput: function( file ) {
        console.log( file );
        this.setState( { singlePartyListFile: file });
    },

    handleOnSubmitSinglePartyList: function( e ) {
        e.preventDefault();

        var _this = this;
        var data = new FormData();
        var config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        data.append( 'file', _this.state.singlePartyListFile );
        data.append( 'countyId', _this.state.countyIdForCandidateList );
        axios.post( '/api/candidate/singlePartyListUpload', data, config ).then( function( response ) {
            console.log( "single party list added" );
            document.getElementById( "countyListMsg" ).innerHTML = `<div class="alert alert-success">
             <strong>Sąrašas sėkmingai užregistruotas</strong>
             </div>`;
            setTimeout( function() {
                document.getElementById( "countyListMsg" ).innerHTML = "";
            }, 4500 );

            axios.get( '/api/county' ).then( function( response ) {
                _this.setState( { counties: response.data });
            }).catch( function( error ) {
                console.log( error );
            });

            axios.get( '/api/candidate' ).then( function( response ) {
                _this.setState( { candidates: response.data });
            }).catch( function( error ) {
                console.log( error );
            });


            _this.setState( { countyIdForCandidateList: "", singlePartyListFile: null });
        }).catch( function( error ) {
            document.getElementById( "countyListMsg" ).innerHTML = `<div class="alert alert-danger">
             <strong>Klaida, nepasirinkta apygarda arba kandidatų sąrašas.</strong>
             </div>`;
            setTimeout( function() {
                document.getElementById( "countyListMsg" ).innerHTML = "";
            }, 4500 );
            console.error( error );

        });

        document.getElementById( "countyCandidateListAddForm" ).reset();
    },


    handleOnChangeInputCountyListIdToDelete: function( el ) {
        console.log( el.target.value );
        this.setState( { countyListIdToDelete: el.target.value });
    },


    handleOnSubmitDeleteCountyCandidateList: function( e ) {
        e.preventDefault();
        var _this = this;
        axios.post( '/api/candidate/county/' + _this.state.countyListIdToDelete ).then( function( response ) {
            console.log( 'county list who\'s ID:' + _this.state.countyListIdToDelete + 'candidates deleted' );
            document.getElementById( "countyListDeletionMsg" ).innerHTML = `<div class="alert alert-success">
             <strong>Apygardos kandidatų sąrašas sėkmingai ištrintas</strong>
             </div>`;
// *** JK
            setTimeout( function() {
                document.getElementById( "countyListDeletionMsg" ).innerHTML = "";
            }, 9000 );
// ***
            axios.get( '/api/candidate' ).then( function( response ) {
                _this.setState( { candidates: response.data });
            }).catch( function( error ) {
                console.log( error );
            });

            axios.get( '/api/county' ).then( function( response ) {
                _this.setState( { counties: response.data });
            }).catch( function( error ) {
                console.log( error );
            });
            _this.setState( { countyListIdToDelete: "" });
// ***            
//  ^         setTimeout( function() {
//  |              document.getElementById( "countyListDeletionMsg" ).innerHTML = "";
//  |          }, 4500 );
// ***            
        }).catch( function( error ) {
            document.getElementById( "countyListDeletionMsg" ).innerHTML = `<div class="alert alert-danger">
             <strong>Pasirinkite apygardą!</strong>
             </div>`;
            setTimeout( function() {
                document.getElementById( "countyListDeletionMsg" ).innerHTML = "";
            }, 4500 );
            console.error( error );

        });



    },

    


    render: function() {
        return ( <AdminPageComponent

            startVoteCounting={this.handleStartVoteCounting}
            partyList={this.handlePartyList}
            countyList={this.handleCountyList}
            countyDeleteList={this.handleCountyDeleteList}
            candidates={this.state.candidates}
            parties={this.state.parties}
            counties={this.state.counties}
            buttonID={this.state.buttonID}
            onClickGetButtonID={this.onClickGetButtonID}
            onClickDeleteCounty={this.handleOnDeleteCounty}
            onSubmitAddCounty={this.handleOnSubmitAddCounty}
            onSubmitAddParty={this.handleOnSubmitAddParty}
            onChangeInputPartyData={this.handleOnChangeInputPartyData}
            onChangeInputCountyData={this.handleOnChangeInputCountyData}
            onChangeInputPartyIdToDelete={this.handleOnChangeInputPartyIdToDelete}
            onSubmitDeleteParty={this.handleOnSubmitDeleteParty}
            onChangeSelectPartyIdForCandidateList={this.handleOnChangeSelectPartyIdForCandidateList}
            onChangeMultiPartyListFileInput={this.handleOnChangeMultiPartyListFileInput}
            onSubmitMultiPartyList={this.handleOnSubmitMultiPartyList}
            onChangeSelectCountyIdForCandidateList={this.handleOnChangeSelectCountyIdForCandidateList}
            onChangeSinglePartyListFileInput={this.handleOnChangeSinglePartyListFileInput}
            onSubmitSinglePartyList={this.handleOnSubmitSinglePartyList}
            onChangeInputCountyListIdToDelete={this.handleOnChangeInputCountyListIdToDelete}
            onSubmitDeleteCountyCandidateList={this.handleOnSubmitDeleteCountyCandidateList}

            /> );
    }
});

AdminPageContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.AdminPageContainer = AdminPageContainer;
