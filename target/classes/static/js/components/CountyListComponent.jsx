var Link = ReactRouter.Link;
var CountyListComponent = React.createClass({

    render: function() {
        var list = this.props.counties.map(function(el) {
            return (
                <tr key={el.countyId}>
                    <td>
                        <div>{el.countyId}</div>
                    </td>
                    <td>
                        <Link to={'/countyDistrictList/'+ el.countyId}>{el.countyName}</Link>
                    </td>
                </tr>
            );
        });

        return (
            <div className="container">
                <div className="row title">
                    <div>Apygardų sąrašas</div>
                </div>

                <table className="table table-striped ">
                    <thead>
                    <tr>

                        <th>
                            <h3 className="subtitle">NR.</h3>
                        </th>

                        <th>
                            <h3 className="subtitle">Apygardos pavadinimas</h3>
                        </th>
                    </tr>
                    </thead>
                    <tbody >
                    {list}
                    </tbody>
                </table>

            </div>
        );
    }
});

window.CountyListComponent = CountyListComponent;
