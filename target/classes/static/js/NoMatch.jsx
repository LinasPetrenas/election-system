var NoMatch = React.createClass({
    render: function() {
        return <div className="text-center">
            <h2>Page Not Found</h2><br/>Route did not match</div>;
    }
});

window.NoMatch = NoMatch;
