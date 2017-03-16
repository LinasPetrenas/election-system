var Well = ReactBootstrap.Well;

var SingleMemberDistrictResultComponent = React.createClass({


    render: function() {

        var props = this.props;
        var _this = this;

        var candidateList = _this.props.candidates.map( function( candidate ) {

            return (


                <SingleMemberDistrictResultElementContainer key={candidate.personCode} personCode={candidate.personCode} districtId={props.districtId} />


            );
        });
        var $table = $('table.tuturuu');
        $table.floatThead({top: 50});
        $table.trigger('reflow');

        const tableBackground = {backgroundColor:'#c4cfe2'};
        return (
            <div className="container">

                <h3 className="text-center title" style={{marginBottom:'10px'}}>Apygarda: {props.county.countyName}</h3>
                <h4 className="text-center subtitle">Balsavimo rezultatai {props.district.districtName} apylinkėje</h4>
                <br/>
                <br/>
                <div className="table-responsive">
                    <Well>
                        <table className="table table-bordered tuturuu">

                            <thead style={
                                tableBackground
                            }>
                            <tr>
                                <th  rowSpan="2">Balsų registravimo laikas</th>

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
                                <td>{props.singleVotesCorruptEntity.dateOnSaving}</td>
                                <td id="totalSingleVotes">{props.districtActivity}</td>
                                <td>{props.districtActivityRate}%</td>
                                <td >{props.singleVotesCorruptEntity.votesSingleCorrupt}</td>
                            </tr>

                            </tbody>
                        </table></Well></div>

                <br/>




                <Well><div className="table-responsive">
                    <table className="table table-bordered">

                        <thead style={
                            tableBackground
                        }>
                        <tr>
                            <th rowSpan="2">Kandidatas</th>
                            <th rowSpan="2">Iškėlė</th>
                            <th colSpan="3">Balsų skaičius</th>

                        </tr>

                        <tr>
                            <th>iš viso</th>
                            <th>% nuo dalyvavusių rinkėjų</th>
                            <th>% nuo galiojančių biuletenių</th>

                        </tr>



                        </thead>
                        <tbody>
                        {candidateList}
                        </tbody>
                    </table></div>
                </Well>





        </div>
        );
    }
});

window.SingleMemberDistrictResultComponent = SingleMemberDistrictResultComponent;
