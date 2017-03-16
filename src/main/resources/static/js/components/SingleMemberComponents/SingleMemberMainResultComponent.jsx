var Link = ReactRouter.Link;
var Button = ReactBootstrap.Button;
var ButtonToolbar = ReactBootstrap.ButtonToolbar;

//Data export to CSV
$(document).on('click', '#export', function() { $("#export_table").tableToCSV();
    });

var SingleMemberMainResultComponent = React.createClass( {



    render: function() {
        var props = this.props;
        var _this = this;
        var countyList = _this.props.counties.map( function( county ) {
            return (
                    <SingleMemberMainResultElementContainer key={county.countyId} countyId={county.countyId} />
            );
        });

        // Floating Header
        var $table = $('table.table');
        $table.floatThead({top: 50});
        $table.trigger('reflow');

        const tableBackground = {backgroundColor:'#c4cfe2'};

        return (


            <div className="container">

                <h3 className="text-center title">Balsavimo rezultatai vienmandatėse apygardose</h3>

                <table id="export_table" className="table table-bordered">

                    <thead style={
                        tableBackground
                    }>
                        <tr>
                            <th rowSpan="2">Apygarda</th>
                            <th rowSpan="2">Rinkėjų skaičius</th>
                            <th colSpan="2">Aktyvumas (dalyvavo)</th>
                            <th rowSpan="2">Sugadintų biuletenių skaičius</th>
                            <th colSpan="2">Apylinkių skaičius</th>
                        </tr>

                        <tr>
                            <th>iš viso</th>
                            <th>% nuo visų rinkėjų</th>
                            <th>iš viso</th>
                            <th>Atsiuntė duomenis</th>
                        </tr>



                    </thead>
                    <tbody>
                        
                            {countyList}
  
                    </tbody>
                </table>
                <ButtonToolbar className="">
                    <Button id="export" bsStyle="primary" bsSize="small">Išsaugoti duomenis CSV formatu</Button>
                </ButtonToolbar>

            </div>

        );
    }
});

window.SingleMemberMainResultComponent = SingleMemberMainResultComponent;
