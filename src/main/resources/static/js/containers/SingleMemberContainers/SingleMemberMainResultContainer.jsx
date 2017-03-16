

var SingleMemberMainResultContainer = React.createClass({
    
    getInitialState: function() {
        return {
            counties: []
   
        };
    },
    
    componentWillMount: function() {
        var _this = this;
        axios.get('/api/county').then(function(response) {
            _this.setState({counties: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        

    },
    

    render: function() {
        return (
            <div><SingleMemberMainResultComponent counties={this.state.counties} /></div>
        );
    }
});

window.SingleMemberMainResultContainer = SingleMemberMainResultContainer;
