var Well = ReactBootstrap.Well;
var MultiMemberDistrictResultComponent = React.createClass( {

    render: function() {
       
        
        var props = this.props;
        var _this = this;
        
        var partyList = _this.props.parties.map( function( party ) {

            return (

                    
                    <MultiMemberDistrictResultElementContainer key={party.partyId} partyId={party.partyId} districtId={props.districtId} />
                   

            );
        });
        var $table = $('table.table');
        $table.floatThead({top: 50});
        $table.trigger('reflow');
        const tableBackground = {backgroundColor:'#c4cfe2'};
        return (
            <div className="container">
                


             
                <h3 className="text-center title" style={{marginBottom:'10px'}}>Apygarda: {props.county.countyName}</h3>
                <h4 className="text-center subtitle">Balsavimo rezultatai {props.district.districtName} apylinkėje</h4>
                <br/>
                <br/>
<Well>
                <table className="table table-bordered">

                    <thead style={
                        tableBackground
                    }>
                        <tr>
                            <th rowSpan="2">Balsų registravimo laikas</th>
                            <th colSpan="2">Aktyvumas (dalyvavo)</th>
                            <th rowSpan="2">Sugadintų biuletenių skaičius</th>
                        </tr>
                        <tr>
                            <th>iš viso</th>
                            <th>% nuo visų rinkėjų</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr>  
                            <td>{props.multiVotesCorruptEntity.dateOnSaving}</td>
                                  <td id="totalMultiVotes">{props.districtActivity}</td>
                                  <td>{props.districtActivityRate}%</td>
                                  <td>{props.multiVotesCorruptEntity.votesMultiCorrupt}</td>
                                  </tr>  
                    </tbody>
                </table>
</Well>
                                  <br />


        <Well>
                                  <table className="table table-bordered">

                                      <thead style={
                                          tableBackground
                                      }>
                                          <tr>
                                              <th rowSpan="2">Partija</th>
                                              <th colSpan="3">Balsų skaičius</th>

                                          </tr>

                                          <tr>
                                              <th>iš viso</th>
                                              <th>% nuo dalyvavusių rinkėjų</th>
                                              <th>% nuo galiojančių biuletenių</th>

                                          </tr>



                                      </thead>
                                      <tbody>
                                              {partyList}
                                             
                                              
                                      </tbody>
                                  </table>
        </Well>

            </div>
        );
    }
});

window.MultiMemberDistrictResultComponent = MultiMemberDistrictResultComponent;
