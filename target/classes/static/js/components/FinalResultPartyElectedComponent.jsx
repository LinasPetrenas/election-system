var Link = ReactRouter.Link;
var Nav = ReactBootstrap.Nav;
var NavItem = ReactBootstrap.NavItem;
var Button = ReactBootstrap.Button;
var Chart = reactChartjs2.Chart;
var Well = ReactBootstrap.Well;




var FinalResultPartyElectedComponent = React.createClass( {
    getInitialState: function() {
        return {finalMandateList:[] };
    },

  
    componentWillMount: function() {

 	var _this = this;

        axios.get( 'api/finalMandateList' ).then( function( response ) {
            _this.setState( { finalMandateList: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

    },

    render: function() {
        var _this = this;



var sorted =_this.state.finalMandateList.sort(function (x, y) {
    var n = y.mandateCount - x.mandateCount;
    if (n != 0) {
        return n;
    }

    return x.partyName.localeCompare(y.partyName);
});



        var list = sorted.map( function( party, index ) {


            return (
                <tr key={index}>
                    <td>
                        {party.partyName} 
                    </td>
                    <td>
                        {party.mandateCount}
                    </td>
                   
                </tr>
            );
       });

        return <div className="container">

        <h3 className=" text-center title">Partijų mandatų skaičius</h3>

            <Nav bsStyle="tabs" activeKey="4">
                <NavItem eventKey="1" className="navigationPanel" href="#/finalResultSingleMemberElected">Išrinktų Seimo narių sąrašas vienmandatėse apygardose</NavItem>
                <NavItem eventKey="2" className="navigationPanel" href="#/finalResultMultiMemberElected" title="Item">Daugiamandatės rinkimų apygardos rezultatai</NavItem>
                <NavItem eventKey="3" className="navigationPanel" href="#/finalResultParlamentMemberElected" >Seimo narių sąrašas</NavItem>
                <NavItem eventKey="4" className="navigationPanel" href="#/finalResultPartyElected" >Partijų mandatų skaičius</NavItem>
            </Nav>
    <Well>
    <table className="table table-bordered">
        <thead>
            <tr>
                <th>Partija</th>
                <th>Mandatų skaičius</th>
            </tr>
        </thead>
        <tbody >
           {list}
        </tbody>
    </table>
        </Well>

            <form action="results/finalPartyMandateList/csv" method="post" id="downloadCSV">
        <Button id="submitId" bsStyle="primary" bsSize="small" type="submit">Eksportuoti CSV</Button>
        </form>
        </div>;
    }
});

window.FinalResultPartyElectedComponent = FinalResultPartyElectedComponent;
