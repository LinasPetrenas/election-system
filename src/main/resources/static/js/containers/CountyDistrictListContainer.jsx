
var CountyDistrictListContainer = React.createClass({
    
    getInitialState: function() {
        return {
            districts: [],
            
        };
    },
    
    
    
  

    componentWillMount: function() {
        var _this = this;

        axios.get('/api/county/districts/' + this.props.params.id).then(function(response) {
            _this.setState({districts: response.data});
        }).catch(function(error) {
            console.log(error);
        });

        
    },

    
    render: function() {
        

        return <CountyDistrictListComponent districts={this.state.districts}/>
    }
});

window.CountyDistrictListContainer = CountyDistrictListContainer;