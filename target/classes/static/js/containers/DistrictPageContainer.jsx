var DistrictPageContainer = React.createClass( {

    getInitialState: function() {
        return {
            districts: [],
            district: {
                districtName: '',
                districtAddress: '',
                districtElectors: '',
                countyId: this.props.params.id
            },
            county: "",
            representatives: []

        };
    },

    reprButton: function( district ) {

        var a = 0;

        for ( var i = 0; i < this.state.representatives.length; i++ ) {
            if ( this.state.representatives[i].districtId == district.id ) {
                ++a;
            }
        }

        if ( a > 0 ) {
            return true;
        } else {
            return false;
        }

    },

    reprFullName: function( district ) {
        for ( var i = 0; i < this.state.representatives.length; i++ ) {
            if ( this.state.representatives[i].districtId == district.id ) {
                return this.state.representatives[i].firstName + " " + this.state.representatives[i].lastName
            }

        }
    },

    componentWillMount: function() {
        var _this = this;

        axios.get( '/api/county/districts/' + this.props.params.id ).then( function( response ) {
            _this.setState( { districts: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

        axios.get( '/api/county/' + this.props.params.id ).then( function( response ) {
            _this.setState( { county: response.data });
        }).catch( function( error ) {
            console.log( error );
        });


        axios.get( '/api/representative' ).then( function( response ) {
            _this.setState( { representatives: response.data });
        }).catch( function( error ) {
            console.log( error );
        });
    },

    handleOnSubmitAddDistrict: function( e ) {
        var _this = this;

        e.preventDefault();

        axios.post( '/api/district', this.state.district ).then( function( response ) {
            console.log( "district created" );
            var countyDistricts = [];

            for ( var index = 0; index < _this.state.districts.length; index++ ) {

                countyDistricts.push( _this.state.districts[index].districtName );

            }

            if ( countyDistricts.includes( _this.state.district.districtName ) ) {
                document.getElementById( "districtCreatedMsg" ).innerHTML = `<div class="alert alert-danger">
                  <strong>Tokia apylinkė jau yra</strong></div>`;
                setTimeout( function() {
                    document.getElementById( "districtCreatedMsg" ).innerHTML = "";
                }, 4500 );
            } else {
                document.getElementById( "districtCreatedMsg" ).innerHTML = `<div class="alert alert-success">
                  <strong>Apylinkė sukurta</strong></div>`;
                setTimeout( function() {
                    document.getElementById( "districtCreatedMsg" ).innerHTML = "";
                }, 4500 );


            }











            axios.get( '/api/county/districts/' + _this.props.params.id ).then( function( response ) {
                _this.setState( { districts: response.data });
            }).catch( function( error ) {
                console.log( error );
            });
        }).catch( function( error ) {
            console.log( error );
        });
        document.getElementById( "districtAddForm" ).reset();

    },

    handleOnClickDeleteDistrict: function( district ) {
        var _this = this;
        return function() {
            axios.delete( '/api/district/' + district).then( function( response ) {
                console.log( "district deleted" );
                axios.get( '/api/county/districts/' + _this.props.params.id ).then( function( response ) {
                    _this.setState( { districts: response.data });
                });
            });
        };

    },

    handleOnChangeInputData: function( field ) {
        var _this = this;
        return function( e ) {
            var district = _this.state.district;
            district[field] = e.target.value;
            _this.setState( { district: district });
        };
    },

    handleOnClickDeleteRepresentative: function( district ) {
        var _this = this;
        return function() {
            axios.delete( '/api/representative/' + district ).then( function( response ) {
                console.log( "representative deleted" );
                axios.post( '/api/auth/' + district ).then( function( response ) {
                });
                axios.get( '/api/representative' ).then( function( response ) {
                    _this.setState( { representatives: response.data });
                });
            }).catch(function(error) {
                document.getElementById("tester").innerHTML = `<div class="alert alert-success">
             <strong>Nepavyko ištrinti atstovo</strong>
             </div>`;

            });
        };


    },

    render: function() {

        return (
            <div>

                <DistrictPageComponent
                    reprFullName={this.reprFullName}
                    reprButton={this.reprButton}
                    countyId={this.props.params.id}
                    county={this.state.county}
                    districts={this.state.districts}
                    onSubmitAddDistrict={this.handleOnSubmitAddDistrict}
                    onChangeInputDistrictData={this.handleOnChangeInputData}
                    onClickDeleteDistrict={this.handleOnClickDeleteDistrict}
                    onClickDeleteRepresentative={this.handleOnClickDeleteRepresentative}

                    />

            </div>
        );
    }
});

window.DistrictPageContainer = DistrictPageContainer;
