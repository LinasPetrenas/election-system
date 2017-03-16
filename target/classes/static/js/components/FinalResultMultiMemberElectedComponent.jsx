var Link = ReactRouter.Link;
var Nav = ReactBootstrap.Nav;
var NavItem = ReactBootstrap.NavItem;
var Button = ReactBootstrap.Button;
var Well = ReactBootstrap.Well;
var ButtonToolbar = ReactBootstrap.ButtonToolbar;


//Data export to CSV
$(document).on('click', '#export_multi', function() { $("#export_table_multi").tableToCSV();
    });

var FinalResultMultiMemberElectedComponent = React.createClass( {
    getInitialState: function() {
        return { parties: [], a:[] };
    },


    componentWillMount: function() {
        var _this = this;

//        axios.get( 'results/multi/allPartyWinners' ).then( function( response ) {
//            _this.setState( { parties: response.data });
//        }).catch( function( error ) {
//            console.log( error );
//        });
        
        
       
        
        axios.get( 'results/multi/allFinalPartyResult' ).then( function( response ) {
            _this.setState( { a: response.data });
        }).catch( function( error ) {
            console.log( error );
        });
    },

    render: function() {
        var _this = this;
        
        var sorted =_this.state.a.sort(function (x, y) {
            var n = y.mandateCount - x.mandateCount;
            if (n != 0) {
                return n;
            }

            return x.partyName.localeCompare(y.partyName);
        });


                var multiResult = sorted.map( function( party, index ) {

                    
                    return (
                        <tr key={index}>
                            <td>
                                {party.partyName}
                            </td>
                            <td>
                               {party.totalVoteCount}
                            </td>
                            <td>
                                 {party.participatedPercent.toFixed(2)} %
                            </td>
                            <td>
                                {party.mandateCount}
                            </td>
                        </tr>
                    );
                });

        return <div className="container">

        <h3 className="text-center title">Daugiamandatės rinkimų apygardos rezultatai</h3>

        <Nav bsStyle="tabs" activeKey="2">
            <NavItem eventKey="1" href="#/finalResultSingleMemberElected">Išrinktų Seimo narių sąrašas vienmandatėse apygardose</NavItem>
            <NavItem eventKey="2" href="#/finalResultMultiMemberElected" title="Item">Daugiamandatės rinkimų apygardos rezultatai</NavItem>
            <NavItem eventKey="3" href="#/finalResultParlamentMemberElected" >Seimo narių sąrašas</NavItem>
            <NavItem eventKey="4" href="#/finalResultPartyElected" >Partijų mandatų skaičius</NavItem>
        </Nav>


        <Well>
            <table  id="export_table_multi" className="table table-bordered">
                <thead>
                <tr>
                    <th rowSpan="2">Partija</th>
                    <th colSpan="2">Gauta balsų</th>
                    <th rowSpan="2">Mandatų skaičius</th>
                </tr>
                <tr>
                    <th>iš viso</th>
                    <th>% nuo dalyvavusių rinkėjų</th>
                </tr>
                </thead>
                <tbody>
                {multiResult}
                </tbody>
            </table>

        </Well>

        <ButtonToolbar className="">
        <Button id="export_multi" bsStyle="primary" bsSize="small">Eksportuoti CSV</Button>
    </ButtonToolbar>
       
    </div>
    }
});

window.FinalResultMultiMemberElectedComponent = FinalResultMultiMemberElectedComponent;
