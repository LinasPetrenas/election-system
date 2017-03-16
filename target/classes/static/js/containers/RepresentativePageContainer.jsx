var singleCandidatesVotes = {};
var singleCandidatesVotesArray = [];
var votesSingleCorrupt = "";

var multiVotes = {};
var multiVotesArray = [];
var votesMultiCorrupt = "";

var RepresentativePageContainer = React.createClass({

    getInitialState: function() {
        return {candidates: [], parties: [], district: {},counties:[]};

    },
    
    componentWillMount: function() {
        var _this = this;

        axios.get('/api/candidate/county/' + _this.props.params.id).then(function(response) {
            _this.setState({candidates: response.data});
        });

        axios.get('/api/party').then(function(response) {
            _this.setState({parties: response.data});
        });

        axios.get('/api/district/' + _this.props.params.id).then(function(response) {
            _this.setState({district: response.data});
        });
        
        axios.get('/api/county').then(function(response) {

            _this.setState({counties: response.data});
        });
        
    },
    
    

    handleOnChangeCandidateCorruptSingleVoteInput: function(e) {
        votesSingleCorrupt = e.target.value;
    },

    
    
   
    
    handleOnChangeCandidateSingleVoteInput: function(e) {
        var votes = e.target.value;
        var personCode = e.target.name;
        singleCandidatesVotes[personCode] = votes;

    },

    handleOnSubmitSingleVoteData: function(e) {
        e.preventDefault();
        var _this = this;
        var singleCandidatesVotesArray = [];
        var date = moment().lang('lt').format('LLL');

        

       

        var corruptVoteObj = {
            countyId: _this.state.district.countyId,
            districtId: _this.props.params.id,
            votesSingleCorrupt: votesSingleCorrupt,
            dateOnSaving:date
        };

        axios.post('/repr/api/singlevotescorrupt', corruptVoteObj).then(function(response) {
            console.log("corrupt votes added");
            document.getElementById("singleCorruptAddedMsg").innerHTML = `<div class="alert alert-success">
             <strong>Negaliojantys vienmandatės apygardos balsai užregistruoti.</strong>
             </div>`;
            _this.setState({partyIdForCandidateList: "", multiPartyListFile: null});
            setTimeout(function() {
                document.getElementById("singleCorruptAddedMsg").innerHTML = "";
            }, 4500);

            votesSingleCorrupt = "";
            for (var personCode in singleCandidatesVotes) {
                singleCandidatesVotesArray.push({"personCode": personCode, "countyId": _this.state.district.countyId, "votesSingleCandidate": singleCandidatesVotes[personCode], "districtId": _this.props.params.id, "dateOnSaving": date});
            }
            
            axios.post('/repr/api/singlevotes/all', singleCandidatesVotesArray).then(function(response) {
                console.log("votes added");
                document.getElementById("singleAddedMsg").innerHTML = `<div class="alert alert-success">
             <strong>Vienmandatės apygardos balsai užregistruoti.</strong>
             </div>`;
                _this.setState({partyIdForCandidateList: "", multiPartyListFile: null});
                setTimeout(function() {
                    document.getElementById("singleAddedMsg").innerHTML = "";
                }, 4500);
                singleCandidatesVotesArray = [];
                singleCandidatesVotes = {};
            });
            
            
        }).catch(function(error) {
            console.log(error);
            axios.post('/repr/api/singlevotes/all', singleCandidatesVotesArray).then(function(response) {
                document.getElementById("singleAddedMsg").innerHTML = `<div class="alert alert-danger">
             <strong>Klaida, nepavyko užregistruoti vienmandatės apygardos balsų</strong>
             </div>`;
                setTimeout(function() {
                    document.getElementById("singleAddedMsg").innerHTML = "";
                }, 4500);
                singleCandidatesVotesArray = [];
                singleCandidatesVotes = {};
            });
            document.getElementById("singleCorruptAddedMsg").innerHTML = `<div class="alert alert-danger">
             <strong>Klaida, nepavyko užregistruoti negaliojančių vienmandatės apygardos balsų</strong>
             </div>`;
            setTimeout(function() {
                document.getElementById("singleCorruptAddedMsg").innerHTML = "";
            }, 4500);

        });

    },

    handleOnChangeCorruptMultiVoteInput: function(e) {
        votesMultiCorrupt = e.target.value;
    },

    handleOnChangeMultiVoteInput: function(e) {

        var votes = e.target.value;
        var partyId = e.target.name;
        multiVotes[partyId] = votes;

    },

    handleOnSubmitMultiVoteData: function(e) {
        e.preventDefault();
        var _this = this;
        var date = moment().lang('lt').format('LLL');
        var multiVotesArray = [];
        var corruptMultiVoteObj = {
            countyId: _this.state.district.countyId,
            districtId: _this.props.params.id,
            votesMultiCorrupt: votesMultiCorrupt,
            dateOnSaving:date
        };

        axios.post('/repr/api/multivotescorrupt', corruptMultiVoteObj).then(function(response) {
            console.log("corrupt multi votes added");
            document.getElementById("multiCorruptAddedMsg").innerHTML = `<div class="alert alert-success">
             <strong>Negaliojantys daugiamandatės apygardos balsai užregistruoti.</strong>
             </div>`;
            _this.setState({partyIdForCandidateList: "", multiPartyListFile: null});
            setTimeout(function() {
                document.getElementById("multiCorruptAddedMsg").innerHTML = "";
            }, 4500);
            votesMultiCorrupt = "";

            for (var partyId in multiVotes) {
                multiVotesArray.push({"partyId": partyId, "countyId": _this.state.district.countyId, "votesMultiParty": multiVotes[partyId], "districtId": _this.props.params.id, dateOnSaving: date});
            }

            axios.post('/repr/api/multivotes/all', multiVotesArray).then(function(response) {
                console.log("multi votes added");
                document.getElementById("multiAddedMsg").innerHTML = `<div class="alert alert-success">
             <strong>Daugiamandatės apygardos balsai užregistruoti.</strong>
             </div>`;
                _this.setState({partyIdForCandidateList: "", multiPartyListFile: null});
                setTimeout(function() {
                    document.getElementById("multiAddedMsg").innerHTML = "";
                }, 4500);
                multiVotesArray = [];
                multiVotes = {};
            });
            
        }).catch(function(error) {
            console.log(error);
            axios.post('/repr/api/multivotes/all', multiVotesArray).then(function(response) {
             document.getElementById("multiAddedMsg").innerHTML = `<div class="alert alert-danger">
             <strong>Klaida, nepavyko užregistruoti daugiamandatės apygardos balsų</strong>
             </div>`;
                setTimeout(function() {
                    document.getElementById("multiAddedMsg").innerHTML = "";
                }, 4500);
                multiVotesArray = [];
                multiVotes = {};
            });
            document.getElementById("multiCorruptAddedMsg").innerHTML = `<div class="alert alert-danger">
             <strong>Klaida, nepavyko užregistruoti negaliojančių daugiamandatės apygardos balsų</strong>
             </div>`;
            setTimeout(function() {
                document.getElementById("multiCorruptAddedMsg").innerHTML = "";
            }, 4500);

        });

    },

    render: function() {

        return (<RepresentativePageComponent counties={this.state.counties} candidates={this.state.candidates} parties={this.state.parties} district={this.state.district} onChangeCandidateSingleVoteInput={this.handleOnChangeCandidateSingleVoteInput} onSubmitSingleVoteData={this.handleOnSubmitSingleVoteData} onChangeCandidateCorruptSingleVoteInput={this.handleOnChangeCandidateCorruptSingleVoteInput} onChangeCorruptMultiVoteInput={this.handleOnChangeCorruptMultiVoteInput} onSubmitMultiVoteData={this.handleOnSubmitMultiVoteData} onChangeMultiVoteInput={this.handleOnChangeMultiVoteInput}/>);
    }
});

window.RepresentativePageContainer = RepresentativePageContainer;
