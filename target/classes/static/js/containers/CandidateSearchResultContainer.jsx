var CandidateSearchResultContainer = React.createClass({

    getInitialState: function() {
        return {
            candidates: [],
            parties : []
        };
    },

    componentWillMount: function() {
        var _this = this;
        axios.get('/api/candidate').then(function(response) {

            _this.setState({candidates: response.data});

        }).catch(function(error) {
            console.log(error);
        });
        
        
        axios.get('/api/party').then(function(response) {

            _this.setState({parties: response.data});
           
        }).catch(function(error) {
            console.log(error);
        });
        
    },

    render: function() {
        return (<CandidateSearchResultComponent candidates={this.state.candidates} parties={this.state.parties} />);
    }
});

window.CandidateSearchResultContainer = CandidateSearchResultContainer;
