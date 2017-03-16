var CountyListContainer = React.createClass({

    getInitialState: function() {
        return {counties: []};
    },

    componentDidMount: function() {
        var _this = this;
        axios.get('/api/county').then(function(response) {

            _this.setState({counties: response.data});

        }).catch(function(error) {
            console.log(error);
        });

    },

    render: function() {
        return (<CountyListComponent counties={this.state.counties}/>);
    }
});

window.CountyListContainer = CountyListContainer;
