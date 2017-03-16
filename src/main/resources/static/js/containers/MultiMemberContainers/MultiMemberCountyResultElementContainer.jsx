var MultiMemberCountyResultElementContainer = React.createClass({
   
    getInitialState: function() {
        return {
            party: {},
            countyVotesParty:"0",
            countyVotesPartyRate:"0",
            countyVotesPartyRateValid:"0"
        
            
            
        };
    },
    
    componentWillMount: function() {
        var _this = this;
        axios.get('/api/party/'+ this.props.partyId).then(function(response) {
            _this.setState({party: response.data});
        }).catch(function(error) {
            console.log(error);
        });
       
        axios.get('results/multi/countypartyvotes/'+ _this.props.countyId +'/'+ this.props.partyId).then(function(response) {
            _this.setState({countyVotesParty: response.data});
        }).catch(function(error) {
            console.log(error);
        });

       
        
        
        axios.get('results/multi/countypartyvotesrate/'+ _this.props.countyId +'/'+ this.props.partyId).then(function(response) {
            _this.setState({countyVotesPartyRate: (response.data).toFixed(2)});
        }).catch(function(error) {
            console.log(error);
        });
      
        
        axios.get('results/multi/countypartyvotesratevalid/'+ _this.props.countyId +'/'+ this.props.partyId).then(function(response) {
            _this.setState({countyVotesPartyRateValid: (response.data).toFixed(2)});
        }).catch(function(error) {
            console.log(error);
        });

      
    },
    
    
    
    render: function() {
        return  <MultiMemberCountyResultElementComponent
        party={this.state.party}
       
        countyVotesParty={this.state.countyVotesParty}
        countyVotesPartyRate={this.state.countyVotesPartyRate}
        countyVotesPartyRateValid={this.state.countyVotesPartyRateValid}
        
        
        />;
    }
});
window.MultiMemberCountyResultElementContainer = MultiMemberCountyResultElementContainer;
