var SingleMemberDistrictResultElementComponent = React.createClass({
    
    render: function() {
        
        var props = this.props;
        
        return <tr>
        <td>{props.candidate.firstName} {props.candidate.lastName}</td>
        <td>{props.partyName}</td>
        <td>{props.candidateDistrictsingleVotes}</td>
        <td>{props.districtCandidateVotesRate}%</td>
        <td>{props.districtCandidateVotesRateValid}%</td>
        </tr>
        ;
    }
});

window.SingleMemberDistrictResultElementComponent = SingleMemberDistrictResultElementComponent;
