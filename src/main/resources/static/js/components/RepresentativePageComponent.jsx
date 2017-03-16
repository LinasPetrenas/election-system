var Accordion = ReactBootstrap.Accordion;
var Panel = ReactBootstrap.Panel;
var Button = ReactBootstrap.Button;
var Modal = ReactBootstrap.Modal;
var RepresentativePageComponent = React.createClass({

    
    
    
    render: function() {
        var props = this.props;
        var _this = this;
       var countyName ="";
        
        //CountyName
        for(var i=0;i< this.props.counties.length;i++){
            
            if(this.props.counties[i].countyId == props.district.countyId){
                countyName =this.props.counties[i].countyName;
            }
            
        }
        
        var partyList = this.props.parties.map(function(el,index) {

            return (
                <tr key={el.partyId}>
                    <td>{el.partyName}</td>
                    <td>
                        <div className="form-group">
                            <input id={`party${index}`} type="number" className="form-control" name={el.partyId} required min="0" onChange={props.onChangeMultiVoteInput}/>
                        </div>
                    </td>
                </tr>
            );
        });

        var candidateList = this.props.candidates.map(function(el,index) {

            return (
                <tr key={el.personCode}>
                    <td>{el.firstName} {el.lastName}</td>
                    <td>
                        <div className="form-group">
                            <input id={`candidate${index}`} type="number" className="form-control" required min="0" name={el.personCode} onChange={props.onChangeCandidateSingleVoteInput}/>
                        </div>
                    </td>
                </tr>
            );
        });

        return (
            <div className="container">

                <div className="text-center" style={{
                    marginBottom: '60px'
                }}>
                    <div className="title">Atstovo puslapis</div>

                    
                    
                    <div className="subtitle" style={{textAlign: 'center'}}>
                        Apygardos pavadinimas: {countyName}<br/>
                        Apylinkės pavadinimas: {props.district.districtName}</div>
                    </div>



                <div className=" container-fluid">

                    <div className="row">
                        <div className="col-sm-6">
                            <form onSubmit={props.onSubmitSingleVoteData}>
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Kandidatas</th>
                                            <th>Balsai</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        {candidateList}

                                        <tr>
                                            <td style={{
                                                color: 'red',
                                                fontWeight: 'bold'
                                            }}>NEGALIOJANTYS BALSAI</td>
                                            <td>
                                                <div className="form-group">
                                                    <input id="corruptCandidate" type="number" className="form-control" required min="0" onChange={props.onChangeCandidateCorruptSingleVoteInput}/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colSpan={2} style={{
                                                textAlign: 'center'
                                            }}>
                                                <button id="submitCandidate" type="submit" className="btn btn-success" style={{
                                                    marginTop: '10px'
                                                }}>ĮKELTI</button>
                                                <div id="singleAddedMsg" style={{
                                                    marginTop: '20px'
                                                }}></div>
                                                <div id="singleCorruptAddedMsg" style={{
                                                    marginTop: '10px'
                                                }}></div>
                                            </td>

                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div className="col-sm-6">
                                <form onSubmit={props.onSubmitMultiVoteData}>
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Partija</th>
                                            <th>Balsai</th>
                                        </tr>
                                    </thead>
                                    <tbody >
                                        {partyList}
                                        <tr>
                                            <td style={{
                                                color: 'red',
                                                fontWeight: 'bold'
                                            }}>NEGALIOJANTYS BALSAI</td>
                                            <td>
                                                <div className="form-group">
                                                    <input id="corruptParty" type="number" className="form-control" required min="0" onChange={props.onChangeCorruptMultiVoteInput}/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colSpan={2} style={{
                                                textAlign: 'center'
                                            }}>
                                                <button id="submitParty" type="submit" className="btn btn-success" style={{
                                                    marginTop: '10px'
                                                }}>ĮKELTI</button>
                                                <div id="multiAddedMsg" style={{
                                                    marginTop: '20px'
                                                }}></div>
                                                <div id="multiCorruptAddedMsg" style={{
                                                    marginTop: '20px'
                                                }}></div>
                                            </td>

                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

window.RepresentativePageComponent = RepresentativePageComponent;
