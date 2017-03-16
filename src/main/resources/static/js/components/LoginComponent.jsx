var Button = ReactBootstrap.Button;
var Modal = ReactBootstrap.Modal;
var LoginComponent = React.createClass( {




    render: function() {
       
        var props = this.props;
        
        return (
            <div className="container">
                <div className="row">

                    <div className="col-sm-12 col-md-4 col-md-offset-4">
                        <div className="title">Prisijungimas</div>
                        <div className="content">
                        <div className="account-wall feature-box">
                            <img className="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                                alt="" />
                            <form onSubmit={props.onSubmitLogin} className="form-signin">
                                <input  onChange={props.onChangeInputLoginData( 'userName' )} type="text" className="form-control" placeholder="Prisijungimo vardas" required />
                                <input  onChange={props.onChangeInputLoginData( 'password' )} type="password" className="form-control" placeholder="Slaptazodis" required />
                                <button className="btn btn-lg btn-primary btn-block" type="submit">Prisijungti</button>
                            </form>
                        </div>
                        </div>
                    </div>
                </div>

                <Modal show={this.props.onFailedLogin} bsSize="small" onHide={this.close}>

                    <Modal.Header style={{ borderBottom:'0px'}}  closeButton>
                        <Modal.Title id="contained-modal-title-sm">Neteisingas vartotojo vardas arba slaptažodis</Modal.Title>
                    </Modal.Header>

                </Modal>
            </div>


        );
    }
});



window.LoginComponent = LoginComponent;
