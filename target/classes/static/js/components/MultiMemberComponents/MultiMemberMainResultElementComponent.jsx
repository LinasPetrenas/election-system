var Link = ReactRouter.Link;

var MultiMemberMainResultElementComponent = React.createClass( {


    render: function() {
        var _this = this;
        var progress = 100 * _this.props.countyDistrictsSendVoteNumber / _this.props.countyDistrictsNumber;
        var divStyle = '';
        if ( progress == 100 ) {
            divStyle = {
                width: `${progress}%`, backgroundImage: 'none',
                backgroundColor: '#5CB85C'
            };
        } else {
            divStyle = { width: `${progress}%` };
        }


        return <tr>
            <td><Link to={'/multiMember/county-result/' + this.props.county.countyId}>{this.props.county.countyName}</Link></td>
            <td>{this.props.countyElectors}</td>
            <td>{this.props.countyActivity}</td>
            <td>{this.props.countyActivityRate} %</td>
            <td>{this.props.countyVotesCorrupt}</td>
            <td>{this.props.countyDistrictsNumber}</td>
            <td>
                <div className="progress" style={{}}>
                    <div className="progress-bar bg-success" role="progressbar" style={divStyle} aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">{this.props.countyDistrictsSendVoteNumber}</div>
                </div>

            </td>
        </tr>
            ;
    }
});

window.MultiMemberMainResultElementComponent = MultiMemberMainResultElementComponent;
