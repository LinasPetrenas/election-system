var Link = ReactRouter.Link;
var Chart = reactChartjs2.Chart;
var Bar = reactChartjs2.Bar;
var Well = ReactBootstrap.Well;


// const data = {
//     labels: ['LDP', 'LPS', 'ADSD', 'FAS', 'RSR', 'LKD', 'TTY', 'LPS', 'ADSD', 'FAS', 'RSR', 'LKD', 'TTY'],
//     datasets: [
//         {
//             label: 'Rezultatai',
//             backgroundColor: 'rgba(81,106,186,0.3)',
//             borderColor: 'rgba(39,50,99,0.5)',
//             borderWidth: 2,
//             hoverBackgroundColor: 'rgba(81,106,186,0.6)',
//             hoverBorderColor: 'rgba(39,50,99,0.5)',
//             data: [65, 59, 80, 81, 56, 55, 40, 59, 80, 81, 56, 55, 40]
//         }
//     ]
// };
//
// var Chart = React.createClass( {
//
//     displayName: 'BarExample',
//     render: function() {
//         return (
//             <div className="chart" >
//                 <Bar
//                     data={data}
//                     width={40}
//                     height={20}
//                     options={{
//                         maintainAspectRatio: true
//                     }}
//                 />
//             </div>
//
//         );
//     }
// });

var MultiMemberMainResultComponent = React.createClass( {


    render: function() {
        // Floating Header
        var $table = $('table.table');
        $table.floatThead({top: 50});
        $table.trigger('reflow');

        const tableBackground = {backgroundColor:'#c4cfe2'};
        var props = this.props;
        var _this = this;
        var countyList = _this.props.counties.map( function( county ) {

            return (

                <MultiMemberMainResultElementContainer key={county.countyId} countyId={county.countyId} />

            );
        });

        return (



            <div className="container">

                <h3 className="text-center title">Balsavimo rezultatai daugiamandatėse apygardose</h3>
                <div className="row">
                    {/*<div className="chart col-md-4">*/}
                        {/*<Well>*/}
                            {/*<div className="subtitle">Partijų mandatai stulpelinėje diagramoje</div>*/}
                            {/*<Chart/>*/}
                        {/*</Well>*/}
                    {/*</div>*/}



                    <div className="chart col-md-12 col-xs-12">
                        <Well>
                            <div className="table-responsive">
                                <table className="table table-bordered">

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
                            </div>
                        </Well>

                    </div>
                </div>
            </div>

        );
    }
});

window.MultiMemberMainResultComponent = MultiMemberMainResultComponent;
