var SingleMemberDistrictResultContainer = React.createClass( {

    getInitialState: function() {
        return {

            county:{},
            districtActivity:"0",
            singleVotesCorruptEntity: {},
            districtActivityRate:"0",
            district:{},
            candidates:[],
            districtId: this.props.params.id
            
            
        };

    },
    
    componentDidMount: function() {
       
        var _this = this;
        
        axios.get('/api/candidate/singleMembercounty/'+ this.props.params.countyId).then(function(response) {
            _this.setState({candidates: response.data});
        }).catch(function(error) {
            console.log(error);
        });
    
        axios.get('/api/county/'+ this.props.params.countyId).then(function(response) {
            _this.setState({county: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        
        axios.get('results/single/districtAktivity/'+ this.props.params.id).then(function(response) {
            _this.setState({districtActivity: response.data});
        }).catch(function(error) {
            console.log(error);
        });

        axios.get('/repr/api/singleVotesCorruptEntity/'+ this.props.params.id).then(function(response) {
           
            _this.setState({singleVotesCorruptEntity: (response.data)});
        }).catch(function(error) {
           
            _this.setState({singleVotesCorruptEntity: {dateOnSaving: "---------", votesSingleCorrupt:"0"}});
            console.log(error);
        });
 
        axios.get('results/single/districtAktivityRate/'+ this.props.params.id).then(function(response) {
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
            <div><SingleMemberDistrictResultComponent
                county={this.state.county}
                districtId={this.state.districtId}
                singleVotesCorruptEntity={this.state.singleVotesCorruptEntity}
                districtActivity={this.state.districtActivity}
                districtActivityRate={this.state.districtActivityRate}
                district={this.state.district}
                candidates={this.state.candidates}
                /></div>
        );
    }
});

window.SingleMemberDistrictResultContainer = SingleMemberDistrictResultContainer;
