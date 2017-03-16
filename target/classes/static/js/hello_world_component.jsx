var Alert = ReactBootstrap.Alert;

var HelloWorldComponent = React.createClass({
    render: function() {
        return (
            <Alert bsStyle='success'>
                <strong>HELLO React-bootstrap</strong>
            </Alert>
        );
    }
});

window.HelloWorldComponent = HelloWorldComponent;
