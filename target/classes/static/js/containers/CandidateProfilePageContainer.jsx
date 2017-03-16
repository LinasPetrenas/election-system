var CandidateProfilePageContainer = React.createClass({

    getInitialState: function() {
        return {candidate: [],partyName:"Išsikėlė pats"};
    },

    componentWillMount: function() {
        var _this = this;
        axios.get('/api/candidate/' + this.props.params.personCode).then(function(response) {

            _this.setState({candidate: response.data});

        }).catch(function(error) {
            console.log(error);
        });

        axios.get('/api/partyByPersoncode/'+ this.props.params.personCode).then(function(response) {
            _this.setState({partyName: (response.data).partyName});
        }).catch(function(error) {
            console.log(error);
        });
        
    },

    render: function() {
        return (<CandidateProfilePageComponent candidate={this.state.candidate} partyName={this.state.partyName}/>);
    }
});

window.CandidateProfilePageContainer = CandidateProfilePageContainer;
