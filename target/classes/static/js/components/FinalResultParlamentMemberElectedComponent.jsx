var Link = ReactRouter.Link;
var Well = ReactBootstrap.Well;
var Nav = ReactBootstrap.Nav;
var NavItem = ReactBootstrap.NavItem;
var Button = ReactBootstrap.Button;


var FinalResultParlamentMemberElectedComponent = React.createClass( {
    getInitialState: function() {
        return { parlamentMembers:[] };
    },


    componentWillMount: function() {
        var _this = this;
        
        axios.get( '/api/formParlamentMembers' ).then( function( response ) {
            _this.setState( { parlamentMembers: response.data });
        }).catch( function( error ) {
            console.log( error );
        });

    },

    render: function() {
        var _this = this;


        var mp = _this.state.parlamentMembers.map( function( mp, index ) {
            return (
                <tr key={mp.fullName}>
                <td>
                        {index+1} 
                    </td>
                    <td>
                        {mp.fullName} 
                    </td>
                    <td>
                        {mp.partyName}
                    </td>
                   
               </tr>
            );
        });

        return <div className="container">


        <h3  className=" text-center title">Seimo narių sąrašas</h3>

       <Nav bsStyle="tabs" activeKey="3">
           <NavItem eventKey="1" href="#/finalResultSingleMemberElected">Išrinktų Seimo narių sąrašas vienmandatėse apygardose</NavItem>
           <NavItem eventKey="2" href="#/finalResultMultiMemberElected" title="Item">Daugiamandatės rinkimų apygardos rezultatai</NavItem>
           <NavItem eventKey="3" href="#/finalResultParlamentMemberElected" >Seimo narių sąrašas</NavItem>
           <NavItem eventKey="4" href="#/finalResultPartyElected" >Partijų mandatų skaičius</NavItem>
       </Nav>

       <Well>
       <table className="table table-bordered">
           <thead>
               <tr>
                <th>Nr.</th>
                   <th>Kandidatas</th>
                   <th>Partinė priklausomybė</th>
               </tr>
           </thead>
           <tbody >
             {mp}
           </tbody>
       </table>
        </Well>
        <form action="results/mp/csv" method="post" id="downloadCSV">
       
        <Button id="submitId" bsStyle="primary" bsSize="small" type="submit">Eksportuoti CSV</Button>
</form>
        </div>
    }
});

window.FinalResultParlamentMemberElectedComponent = FinalResultParlamentMemberElectedComponent;
