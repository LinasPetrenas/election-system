var SingleMemberCountyResultElementComponent = React.createClass({
    render: function() {
        return <tr>
        <td>{this.props.candidate.firstName} {this.props.candidate.lastName}</td>
        <td>{this.props.partyName}</td>
        <td>{this.props.countyVotesCandidate}</td>
        <td>{this.props.countyVotesCandidateRate}%</td>
        <td>{this.props.countyVotesCandidateRateValid}%</td>
        </tr>
        ;
    }
});

window.SingleMemberCountyResultElementComponent = SingleMemberCountyResultElementComponent;
