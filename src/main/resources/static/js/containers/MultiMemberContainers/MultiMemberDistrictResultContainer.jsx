var MultiMemberDistrictResultContainer = React.createClass({

    getInitialState: function() {
        return {

            county:{},
            districtActivity:"0",
            multiVotesCorruptEntity: {},
            districtActivityRate:"0",
            district:{},
            parties:[],
            districtId: this.props.params.id
            
            
        };

    },
    
    componentDidMount: function() {
       
        var _this = this;
        
        axios.get('/api/party/').then(function(response) {
            _this.setState({parties: response.data});
        }).catch(function(error) {
            console.log(error);
        });
    
        axios.get('/api/county/'+ this.props.params.countyId).then(function(response) {
            _this.setState({county: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        
        axios.get('results/multi/activity/'+ this.props.params.id).then(function(response) {
            _this.setState({districtActivity: (response.data)});
        }).catch(function(error) {
            console.log(error);
        });

        axios.get('/repr/api/multiVotesCorruptEntity/'+ this.props.params.id).then(function(response) {
           
            _this.setState({multiVotesCorruptEntity: (response.data)});
        }).catch(function(error) {
           
            _this.setState({multiVotesCorruptEntity: {dateOnSaving: "---------", votesMultiCorrupt:"0"}});
            console.log(error);
        });
 
        axios.get('results/multi/activityrate/'+ this.props.params.id).then(function(response) {
            _this.setState({districtActivityRate: (response.data).toFixed(2)});
        }).catch(function(error) {
            console.log(error);
        });
        axios.get('/api/district/' + this.props.params.id).then(function(response) {
            _this.setState({district: response.data});
        });
    },
    


    render: function() {
        return (
            <div><MultiMemberDistrictResultComponent
                county={this.state.county}
                districtId={this.state.districtId}
                multiVotesCorruptEntity={this.state.multiVotesCorruptEntity}
                districtActivity={this.state.districtActivity}
                districtActivityRate={this.state.districtActivityRate}
                district={this.state.district}
                parties={this.state.parties}
                /></div>
        );
    }
});

window.MultiMemberDistrictResultContainer = MultiMemberDistrictResultContainer;
