var Well = ReactBootstrap.Well;

var CountyDistrictListComponent = React.createClass({
    render: function() {

        var props = this.props;
        var _this = this;

        var districtList = this.props.districts.map(function(district) {
            return (
                <tr key={district.id}>
                    <td className="tableText">
                        <div>{district.districtName}</div>
                    </td>

                    <td className="tableText">
                        <div>{district.districtAddress}</div>
                    </td>
                    <td className="tableText">
                        <div>{district.districtElectors}</div>
                    </td>

                   
                </tr>
            );
        });

        return (
                <div>
                <Well className="row">
                    <div>
                <div className="subtitle">Apylinkių sąrašas</div>
                <hr/>
                <br/>

                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Pavadinimas</th>
                            <th>Adresas</th>
                            <th>Rinkėjų skaičius</th>
                         
                        </tr>
                    </thead>
                    <tbody >
                        {districtList}
                    </tbody>
                </table>
                    </div>
                </Well>
            </div>
        );
    }
});

window.CountyDistrictListComponent = CountyDistrictListComponent;
