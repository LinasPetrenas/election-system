var MultiMemberCountyResultContainer = React.createClass({
    
    getInitialState: function() {
        return {
            county: {},
            parties:[],
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
        
        axios.get('/api/party/').then(function(response) {
            _this.setState({parties: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        
        // LAIKINAS ?
        axios.get('/api/county/districts/'+ this.props.params.id).then(function(response) {
            _this.setState({districts: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        

        axios.get('/repr/api/multivotescorrupt').then(function(response) {

            _this.setState({districtResultTime: (response.data)});
        }).catch(function(error) {
            console.log(error);
        });
    },
    

    render: function() {
        return (
            <div><MultiMemberCountyResultComponent 
                county={this.state.county}
                parties={this.state.parties}
                districts={this.state.districts}
                districtResultTime={this.state.districtResultTime}
                countyId={this.props.params.id}
                /></div>
        );
    }
});

window.MultiMemberCountyResultContainer = MultiMemberCountyResultContainer;
