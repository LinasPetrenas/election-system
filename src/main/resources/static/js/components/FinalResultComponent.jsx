var Link = ReactRouter.Link;
var Tabs = ReactBootstrap.Tabs;
var Tab = ReactBootstrap.Tab;

var a = [];
var FinalResultComponent = React.createClass({

    render: function() {
        var _this = this;
        var props = this.props;

        return (
            <div className="container">

                <div><Link to='/finalResultSingleMemberElected' activeClassName="active">Išrinktų Seimo narių sąrašas vienmandatėse apygardose</Link>
<Link to='/finalResultMultiMemberElected' activeClassName="active">Daugiamandatės rinkimų apygardos rezultatai</Link>
<Link to='/finalResultParlamentMemberElected' activeClassName="active">Seimo narių sąrašas</Link>
<Link to='/finalResultPartyElected' activeClassName="active">Partijų mandatų skaičius</Link></div>

            </div>
        );
    }
});

window.FinalResultComponent = FinalResultComponent;
