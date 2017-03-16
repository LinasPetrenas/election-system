var Link = ReactRouter.Link;

var NavigationBarComponent = React.createClass({

    getInitialState: function() {
        return {candidate: ""}
    },


    render: function() {
        return (
                <header id="header">
                    <nav className="main-nav navbar-fixed-top headroom" role="navigation">
                        <div className="container">
                            <ul className="nav navbar-nav navbar-left">
                                <li className="nav-item pull-left">
                                    <Link to="/" className="icon">
                                        <i className="glyphicon glyphicon-home" style={{fontSize: '28px',top:'-2px'}} ></i>
                                    </Link>
                                </li>
                                <li className="nav-item pull-left">
                                    <Link to="/search-result" className="icon">
                                        <i className="glyphicon glyphicon-search" style={{fontSize: '28px',top:'-2px'}} ></i>
                                    </Link>
                                </li>
                            </ul>



                            <div className="collapse navbar-collapse">
                                <ul className="nav navbar-nav navbar-right">
                                    <li className="nav-item">
                                        
                                    </li>

                                    <li className="nav-item">
                                        <Link to="/login">
                                            <i className="glyphicon glyphicon-log-in" style={{fontSize: '28px',top:'-2px'}} ></i>
                                        </Link>
                                    </li>
                                </ul>
                            </div>

                        </div>
                    </nav>

                </header>
        );

    }

});

NavigationBarComponent.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.NavigationBarComponent = NavigationBarComponent;
