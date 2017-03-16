var SingleMemberCountyResultElementContainer = React.createClass({
   
    getInitialState: function() {
        return {
            candidate: {},
            partyName:"Išsikėlė pats",
            countyVotesCandidate:"0",
            countyVotesCandidateRate:"0",
            countyVotesCandidateRateValid:"0"
        
            
            
        };
    },
    
    componentWillMount: function() {
        var _this = this;
        axios.get('/api/candidate/'+ this.props.personCode).then(function(response) {
            _this.setState({candidate: response.data});
        }).catch(function(error) {
            console.log(error);
        });
       
        axios.get('results/single/countyVotesCandidate/'+ _this.props.countyId +'/'+ this.props.personCode).then(function(response) {
            _this.setState({countyVotesCandidate: response.data});
        }).catch(function(error) {
            console.log(error);
        });

       
        
        
        axios.get('results/single/countyVotesCandidateRate/'+ _this.props.countyId +'/'+ this.props.personCode).then(function(response) {
            _this.setState({countyVotesCandidateRate: (response.data).toFixed(2)});
        }).catch(function(error) {
            console.log(error);
        });
      
        
        axios.get('results/single/countyVotesCandidateRateValid/'+ _this.props.countyId +'/'+ this.props.personCode).then(function(response) {
            _this.setState({countyVotesCandidateRateValid: (response.data).toFixed(2)});
        }).catch(function(error) {
            console.log(error);
        });

        axios.get('/api/partyByPersoncode/'+ this.props.personCode).then(function(response) {
            _this.setState({partyName: (response.data).partyName});
        }).catch(function(error) {
            console.log(error);
        });
    },
    
    
    
    render: function() {
        return  <SingleMemberCountyResultElementComponent
        candidate={this.state.candidate}
        partyName={this.state.partyName}
        countyVotesCandidate={this.state.countyVotesCandidate}
        countyVotesCandidateRate={this.state.countyVotesCandidateRate}
        countyVotesCandidateRateValid={this.state.countyVotesCandidateRateValid}
        
        
        />;
    }
});
window.SingleMemberCountyResultElementContainer = SingleMemberCountyResultElementContainer;
