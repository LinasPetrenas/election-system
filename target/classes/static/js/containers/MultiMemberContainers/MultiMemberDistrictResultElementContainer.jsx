var MultiMemberDistrictResultElementContainer = React.createClass( {

    getInitialState: function() {
        return {
            party: {},
           
            districtPartyVotesRate: "0",
            districtPartyVotesRateValid: "0",
            partyDistrictMultiVotes: "0"


        };
    },

    componentWillMount: function() {

        var _this = this;


        axios.get( '/api/party/' + this.props.partyId ).then( function( response ) {
            _this.setState( { party: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

        axios.get( 'results/multi/partyvotesrate/' + this.props.districtId + "/" + this.props.partyId ).then( function( response ) {
          
            
            _this.setState( { districtPartyVotesRate: ( response.data ).toFixed( 2 ) });
        }).catch( function( error ) {
            console.log( error );
        });


        axios.get( 'results/multi/partyvotesratevalid/' + this.props.districtId + "/" + this.props.partyId ).then( function( response ) {
            _this.setState( { districtPartyVotesRateValid: ( response.data ).toFixed( 2 ) });
        }).catch( function( error ) {
            console.log( error );
        });
        
        axios.get( 'results/multi/partyvotesindistrict/' + this.props.districtId + "/" + this.props.partyId ).then( function( response ) {
           
            
            _this.setState( { partyDistrictMultiVotes: ( response.data ) });
        }).catch( function( error ) {
            console.log( error );
        });

        
      

   

    },

    render: function() {
        return <MultiMemberDistrictResultElementComponent
            party={this.state.party}
          
        districtPartyVotesRate={this.state.districtPartyVotesRate}
        districtPartyVotesRateValid={this.state.districtPartyVotesRateValid}
        partyDistrictMultiVotes={this.state.partyDistrictMultiVotes}
            />;
    }
});
window.MultiMemberDistrictResultElementContainer = MultiMemberDistrictResultElementContainer;
