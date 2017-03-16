var MultiMemberDistrictResultElementComponent = React.createClass({
    
    render: function() {
        
        var props = this.props;
        
        return <tr>
        <td>{props.party.partyName}</td>
        <td>{props.partyDistrictMultiVotes}</td>
        <td>{props.districtPartyVotesRate}%</td>
        <td>{props.districtPartyVotesRateValid}%</td>
        </tr>
        ;
    }
});

window.MultiMemberDistrictResultElementComponent = MultiMemberDistrictResultElementComponent;
