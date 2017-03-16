var SingleMemberCountyResultContainer = React.createClass({
    
    getInitialState: function() {
        return {
            county: {},
            candidates:[],
            districts:[],
            districtsTime:[],
            districtResultTime:[]
        };
    },
    
    componentWillMount: function() {
        var _this = this;
        axios.get('/api/county/' + this.props.params.id).then(function(response) {
            _this.setState({county: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        
        axios.get('/api/candidate/singleMembercounty/'+ this.props.params.id).then(function(response) {
            _this.setState({candidates: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        
        // LAIKINAS ?
        axios.get('/api/county/districts/'+ this.props.params.id).then(function(response) {
            _this.setState({districts: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        

        axios.get('/repr/api/singlevotescorrupt').then(function(response) {

            _this.setState({districtResultTime: (response.data)});
        }).catch(function(error) {
            console.log(error);
        });
    },
    

    render: function() {
        return (
            <div><SingleMemberCountyResultComponent 
                county={this.state.county}
                candidates={this.state.candidates}
                districts={this.state.districts}
                districtResultTime={this.state.districtResultTime}
                countyId={this.props.params.id}
                /></div>
        );
    }
});

window.SingleMemberCountyResultContainer = SingleMemberCountyResultContainer;
