var SingleMemberDistrictResultElementContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidate: {},
            partyName: "Išsikėlė pats",
            districtCandidateVotesRate: "0",
            districtCandidateVotesRateValid: "0",
            candidateDistrictsingleVotes: "0"


        };
    },

    componentWillMount: function() {

        var _this = this;


        axios.get( '/api/candidate/' + this.props.personCode ).then( function( response ) {
            _this.setState( { candidate: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

        axios.get( 'results/single/districtCandidateVotesRate/' + this.props.districtId + "/" + this.props.personCode ).then( function( response ) {
            _this.setState( { districtCandidateVotesRate: ( response.data ).toFixed( 2 ) });
        }).catch( function( error ) {
            console.log( error );
        });


        axios.get( 'results/single/districtCandidateVotesRateValid/' + this.props.districtId + "/" + this.props.personCode ).then( function( response ) {
            _this.setState( { districtCandidateVotesRateValid: ( response.data ).toFixed( 2 ) });
        }).catch( function( error ) {
            console.log( error );
        });



        //        axios.get('repr/api/singlevotes/'+this.props.personCode).then(function(response) {
        //
        //            _this.setState({candidateDistrictsingleVotes:(response.data)[0].votesSingleCandidate});
        //        }).catch(function(error) {
        //            console.log(error);
        //        });

        
        		
        axios.get( 'results/single/districtVotesCandidate/'+this.props.districtId+'/' + this.props.personCode ).then( function( response ) {
            _this.setState( { candidateDistrictsingleVotes: ( response.data ) });
        }).catch( function( error ) {
            console.log( error );
        });


        axios.get( '/api/partyByPersoncode/' + this.props.personCode ).then( function( response ) {
            _this.setState( { partyName: ( response.data ).partyName });
        }).catch( function( error ) {
            console.log( error );
        });

    },

    render: function() {
        return <SingleMemberDistrictResultElementComponent
            candidate={this.state.candidate}
            partyName={this.state.partyName}
            districtCandidateVotesRate={this.state.districtCandidateVotesRate}
            districtCandidateVotesRateValid={this.state.districtCandidateVotesRateValid}
            candidateDistrictsingleVotes={this.state.candidateDistrictsingleVotes}
            />;
    }
});
window.SingleMemberDistrictResultElementContainer = SingleMemberDistrictResultElementContainer;
