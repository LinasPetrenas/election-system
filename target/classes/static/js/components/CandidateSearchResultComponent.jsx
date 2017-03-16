var Link = ReactRouter.Link;
var Well = ReactBootstrap.Well;

var CandidateSearchResultComponent = React.createClass( {

    getInitialState: function() {
        return { displayedCandidates: [], searchBool: true };
    },

    handleSearch: function( event ) {
        var _this = this;
        var searchQuery = event.target.value.toLowerCase();
        var displayedCandidates = this.props.candidates.filter( function( el ) {
            var searchValue = ( el.firstName.toLowerCase() ) + " " + ( el.lastName.toLowerCase() );

            if ( searchValue.indexOf( searchQuery ) !== 1 ) {
                _this.setState( { searchBool: false });
            }

            return searchValue.indexOf( searchQuery ) !== -1;
        });

        this.setState( { displayedCandidates: displayedCandidates });
    },

    render: function() {
        var props = this.props;
        
        if ( this.state.searchBool ) {

            var list = this.props.candidates.map( function( candidate ) {
                var bool = true;    
            
                var partyName = props.parties.map( function( party ) {

                    if ( candidate.partyId == party.partyId ) {
                        bool = false;
                        return party.partyName;
                    }
                });

                if(bool){
                    partyName = "Išsikėlė pats" 
                }
                
                return (
                    <tr key={candidate.personCode}>
                        <td className="tableFix">{candidate.firstName}</td>
                        <td className="tableFix">{candidate.lastName}</td>
                        <td className="tableFix">{candidate.date}</td>
                        <td className="tableFix">{partyName}</td>
                        <td>
                            <Link to={'/candidate-profile/' + candidate.personCode} className="btn btn-info">Kandidato Info</Link>
                        </td>
                    </tr>
                );
            });

        } else {

            var list = this.state.displayedCandidates.map( function( el ) {
                var bool = true;  
                var partyName = props.parties.map( function( party ) {

                    if ( el.partyId == party.partyId ) {
                        bool = false
                        return party.partyName;
                    }
                });
                if(bool){
                    partyName = "Išsikėlė pats" 
                }
                return (
                    <tr key={el.personCode}>
                        <td>{el.firstName}</td>
                        <td>{el.lastName}</td>
                        <td>{el.date}</td>
                        <td>{partyName}</td>
                        <td>
                            <Link to={'/candidate-profile/' + el.personCode} className="btn btn-info">Kandidato Info</Link>
                        </td>
                    </tr>
                );
            });

        }

        return (
            <div className="container">


                <div className="text-center title">Kandidato Paieška</div>

                <hr /><br />

                <div style={{ width: '30%', margin: '0 auto' }} className="input-group">
                    <input id="candidateSearch" type="text" className="form-control" placeholder="Kandidato Paieška..." onChange={this.handleSearch} />
                </div>

                <br /><hr />
                <Well>
                <div style={{ color: '#273263', fontSize: '20px' }} className="text-center"><h2>Kandidatų sąrašas</h2></div><br />
                <table style={{ width: '90%', margin: '0 auto' }} className="table table-striped">
                    <thead>
                        <tr>
                            <th>Vardas</th>
                            <th>Pavardė</th>
                            <th>Gimimo Data</th>
                            <th>Partija</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody >
                        {list}
                    </tbody>
                </table>
                </Well>
            </div>
        );
    }
});

window.CandidateSearchResultComponent = CandidateSearchResultComponent;
