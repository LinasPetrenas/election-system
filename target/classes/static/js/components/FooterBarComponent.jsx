var Link = ReactRouter.Link;

var FooterBarComponent = React.createClass({

    render: function() {
        return (

            <div className="footer fixed-bottom">
                <div className="container">
                    <div>
                       GrupėIrNumeris
                    </div>
                    <div>
                        2017
                    </div>
                </div>
            </div>

        );

    }

});

FooterBarComponent.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.FooterBarComponent = FooterBarComponent;
