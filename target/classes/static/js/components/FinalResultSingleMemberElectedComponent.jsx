var Link = ReactRouter.Link;
var Nav = ReactBootstrap.Nav;
var NavItem = ReactBootstrap.NavItem;
var Button = ReactBootstrap.Button;
var Well = ReactBootstrap.Well;
var ButtonToolbar = ReactBootstrap.ButtonToolbar;

//Data export to CSV
$(document).on('click', '#export_single', function() { $("#export_table_single").tableToCSV();
    });

var FinalResultSingleMemberElectedComponent = React.createClass( {
    getInitialState: function() {
        return { winners: [], a:[] };
    },


    componentWillMount: function() {

        var _this = this;

//         axios.get( 'results/single/winner/all' ).then( function( response ) {
//           
//        }).catch( function( error ) {
//            console.log( error );
//        });
//         
//        
//         axios.get( 'results/multi/winnerswinners' ).then( function( response ) {
//             
//         }).catch( function( error ) {
//             console.log( error );
//         });

//        axios.get( 'results/single/countyWinners' ).then( function( response ) {
//            _this.setState( { winners: response.data });
//        }).catch( function( error ) {
//            console.log( error );
//        });

        
        
        axios.get( 'api/singleFinalList' ).then( function( response ) {
            _this.setState( { a: response.data });
        }).catch( function( error ) {
            console.log( error );
        });


    },
    
    

    render: function() {
        var _this = this;
       
        

//
//        var list = _this.state.winners.map( function( winner, index ) {
//
//
//            return (
//                <tr key={index}>
//                    <td>
//                        {winner.firstName} {winner.lastName}
//                    </td>
//                        <td>
//                        {winner.partyName}
//                    </td>
//                    <td>
//                        {winner.countyName}
//                    </td>
//                    <td>
//                         {winner.countyWinnerPercents.toFixed(2)}%
//                    </td>
//                </tr>
//            );
//        });

        var sorted =_this.state.a.sort(function (x, y) {
           

            return x.countyName.localeCompare(y.countyName);
        });

        var list = sorted.map( function( winner, index ) {


            return (
                <tr key={index}>
                    <td>
                        {winner.firstName} {winner.lastName}
                    </td>
                        <td>
                        {winner.partyName}
                    </td>
                    <td>
                        {winner.countyName}
                    </td>
                    <td>
                         {winner.countyWinnerPercents.toFixed(2)}%
                    </td>
                </tr>
            );
        });
        
        return <div className="container">

        <h3 className=" text-center title">Išrinktų Seimo narių sąrašas vienmandatėse apygardose</h3>

        <Nav bsStyle="tabs" activeKey="1">
            <NavItem eventKey="1" href="#/finalResultSingleMemberElected">Išrinktų Seimo narių sąrašas vienmandatėse apygardose</NavItem>
            <NavItem eventKey="2" href="#/finalResultMultiMemberElected" title="Item">Daugiamandatės rinkimų apygardos rezultatai</NavItem>
            <NavItem eventKey="3" href="#/finalResultParlamentMemberElected" >Seimo narių sąrašas</NavItem>
            <NavItem eventKey="4" href="#/finalResultPartyElected" >Partijų mandatų skaičius</NavItem>
        </Nav>
        <Well>
            <table  id="export_table_single" className="table table-bordered">
                <thead>
                <tr>
                    <th>Kandidatas</th>
                    <th>Iškėlė</th>
                    <th>Apygarda</th>
                    <th>% balsų</th>
                </tr>
                </thead>
                <tbody >
                {list}
                </tbody>
            </table>


        </Well>

        <ButtonToolbar className="">
        <Button id="export_single" bsStyle="primary" bsSize="small">Eksportuoti CSV</Button>
    </ButtonToolbar>
        </div>;
    }
});

window.FinalResultSingleMemberElectedComponent = FinalResultSingleMemberElectedComponent;
