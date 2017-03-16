var MultiMemberMainResultElementContainer = React.createClass({

    getInitialState: function() {
        return {
            county: {},
            countyElectors:"",
            countyActivity:"",
            countyActivityRate:"",
            countyVotesCorrupt:"",
            countyDistrictsNumber:"",
            countyDistrictsSendVoteNumber:""


        };
    },

    componentWillMount: function() {
        var _this = this;
        axios.get('/api/county/'+ this.props.countyId).then(function(response) {
            _this.setState({ county: response.data});
        }).catch(function(error) {
            console.log(error);
        });


        var _this = this;
        axios.get('results/multi/countyelectors/' + this.props.countyId).then(function(response) {
            _this.setState({countyElectors: response.data});
        }).catch(function(error) {
            console.log(error);
        });

        axios.get('results/multi/countyactivity/' + this.props.countyId).then(function(response) {
          _this.setState({countyActivity: response.data});
      }).catch(function(error) {
          console.log(error);
      });

        axios.get('results/multi/countyactivityrate/'  + this.props.countyId).then(function(response) {
          _this.setState({countyActivityRate: (response.data).toFixed(2)});
      }).catch(function(error) {
          console.log(error);
      });

      axios.get('results/multi/countyvotescorrupt/' + this.props.countyId).then(function(response) {
      _this.setState({countyVotesCorrupt: response.data});
      }).catch(function(error) {
      console.log(error);
      });

      axios.get('results/multi/countydistrictnumber/' + this.props.countyId).then(function(response) {
        _this.setState({countyDistrictsNumber: response.data});
      }).catch(function(error) {
          console.log(error);
      });

      axios.get('results/multi/countydistrictsenddata/' + this.props.countyId).then(function(response) {
          _this.setState({countyDistrictsSendVoteNumber: response.data});
      }).catch(function(error) {
          console.log(error);
      });


    },




    render: function() {
        return  <MultiMemberMainResultElementComponent
        county={this.state.county}
        countyElectors={this.state.countyElectors}
        countyActivity={this.state.countyActivity}
        countyActivityRate={this.state.countyActivityRate}
        countyVotesCorrupt={this.state.countyVotesCorrupt}
        countyDistrictsNumber={this.state.countyDistrictsNumber}
        countyDistrictsSendVoteNumber={this.state.countyDistrictsSendVoteNumber}
        />;
    }
});

window.MultiMemberMainResultElementContainer = MultiMemberMainResultElementContainer;
