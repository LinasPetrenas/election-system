var MultiMemberCountyResultElementComponent = React.createClass({
    render: function() {
        return <tr>
        <td>{this.props.party.partyName}</td>
        <td>{this.props.countyVotesParty}</td>
        <td>{this.props.countyVotesPartyRate}%</td>
        <td>{this.props.countyVotesPartyRateValid}%</td>
        </tr>
        ;
    }
});

window.MultiMemberCountyResultElementComponent = MultiMemberCountyResultElementComponent;
