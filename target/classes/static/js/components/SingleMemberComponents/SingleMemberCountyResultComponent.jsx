var Link = ReactRouter.Link;
var Well = ReactBootstrap.Well;
var SingleMemberCountyResultComponent = React.createClass({
    render: function() {
        var props = this.props;
        var _this = this;

        var candidateList = _this.props.candidates.map( function(candidate) {

            return (

                <SingleMemberCountyResultElementContainer key={candidate.personCode} personCode={candidate.personCode} countyId={props.county.countyId} />

            );
        });


        var districtList = _this.props.districts.map( function( district ) {

            var districtResultTime = props.districtResultTime.map(function(el){

                if(district.id == el.districtId){
                    return el.dateOnSaving;
                }
            });


            return (


                <tr key={district.id}>
                    <td><Link to={"/singleMember/district-result/"+props.countyId+"/" + district.id} >{district.districtName}</Link></td>
                    <td >{districtResultTime}</td>
                </tr>

            );
        });
        var $table = $('table.tableD');
        $table.floatThead({top: 50});
        $table.trigger('reflow');

        const tableBackground = {backgroundColor:'#c4cfe2'};

        return (



            <div className="container">

                <h3 className="text-center title">Balsavimo rezultatai {this.props.county.countyName} apygardoje</h3>
                <Well>
                    <div className="table-responsive">
                    <table className="tableD table table-bordered">

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
                    </table>
                    </div>
                </Well>
                <br/>

                <Well> <div className="table-responsive ">
                    <table style={{ width: '60%', margin:'0 auto' }} className="table table-bordered text-center">

                        <thead style={
                            tableBackground
                        }>
                        <tr>
                            <th>Apylinkė</th>
                            <th>Rezultatas pateiktas</th>

                        </tr>

                        </thead>
                        <tbody>

                        {districtList}

                        </tbody>
                    </table> </div>
                </Well>

            </div>

        );
    }
});

window.SingleMemberCountyResultComponent = SingleMemberCountyResultComponent;
