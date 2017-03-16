var Well = ReactBootstrap.Well;
var Link = ReactRouter.Link;
var AddRepresentativeComponent = React.createClass({


    render: function() {
        var _this = this;
        var props = this.props;


        return (

            <div className="container">

                <br/>
                <h2 className="title">Atstovo registravimas</h2>
                <Well>
                    <br/>

                    <div id="representativeCreationDialog"></div>
                    <div className="row">

                            <form className="col-sm-12 col-md-4 col-md-offset-4" onSubmit={this.props.onSubmitAddRepresentativeData}>
                                <div className="form-group row">
                                    <label htmlFor="example-text-input1" className="col-2 col-form-label">Atstovo vardas</label>
                                    <div className="col-10">
                                        <input className="form-control" required type="text" id="example-text-input1" onChange={props.onChangeInputRepresentativeData('firstName')}/>
                                    </div>
                                </div>
                                <div className="form-group row">
                                    <label htmlFor="example-search-input1" className="col-2 col-form-label">Atstovo pavardė</label>
                                    <div className="col-10">
                                        <input className="form-control" required  type="text" id="example-search-input1" onChange={props.onChangeInputRepresentativeData('lastName')}/>
                                    </div>
                                </div>
                                <div className="form-group row">
                                    <label htmlFor="example-email" className="col-2 col-form-label">Atstovo vartotojo vardas</label>
                                    <div className="col-10">
                                        <input className="form-control" required  type="text" id="example-email" onChange={props.onChangeInputRepresentativeData('userName')}/>
                                    </div>
                                </div>
                                <div className="form-group row">
                                    <label htmlFor="example-email-input1" className="col-2 col-form-label">Atstovo slaptažodis</label>
                                    <div className="col-10">
                                        <input className="form-control" required  type="password" id="example-email-input1" onChange={props.onChangeInputRepresentativeData('password')}/>
                                    </div>

                                </div>
                                <button style={{
                                    marginLeft: '30px'
                                }} type="submit" className="btn btn-primary">
                                    <strong>Sukurti</strong>
                                </button>
                                <Link to={"/county/districts/" + props.districtId } className="text-right">
                                    <button className="btn btn-danger" style={{
                                        marginLeft: '50px'
                                    }}>Grįžti atgal</button>
                                </Link>

                            </form>

                    </div>

                </Well>
            </div>
        );
    }
});

window.AddRepresentativeComponent = AddRepresentativeComponent;
