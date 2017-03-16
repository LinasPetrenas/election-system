var Modal = ReactBootstrap.Modal;
var LoginContainer = React.createClass({

    close() {
        this.setState({showModal: false});
    },
    openModal() {
        this.setState({showModal: true});
        setTimeout(function() {
            this.setState({showModal: false});
        }.bind(this), 3000);
    },
    getInitialState: function() {

        return {
            showModal: false,
            user: {
                userName: "",
                password: ""
            },
            userExist: []
        };

    },

    handleOnChangeInputLoginData: function(field) {
        var _this = this;
        return function(e) {
            var userData = _this.state.user;
            userData[field] = e.target.value;
            _this.setState({user: userData});
        };
    },

    handleOnSubmitLogin: function(e) {
        e.preventDefault();
        var _this = this;
        axios.post('/api/auth/login', this.state.user).then(function(response) {
            console.log(`logged as ${response.data.userName}`);
            _this.setState({userExist: response.data});
            if (_this.state.userExist.userName === "admin") {
                _this.context.router.push('/admin');
            } else if (_this.state.userExist.userName != null) {
                _this.context.router.push('/representative/' + _this.state.userExist.id);
            }
        }).catch(function(error) {
            console.log(error);
            _this.openModal();
            // _this.setState({showModal: true});
        });

    },

    render: function() {

        return (<LoginComponent onChangeInputLoginData={this.handleOnChangeInputLoginData}
                                onSubmitLogin={this.handleOnSubmitLogin}
                                onFailedLogin={this.state.showModal}
                                closeModal={this.close}
        />);

    }
});

LoginContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.LoginContainer = LoginContainer;
