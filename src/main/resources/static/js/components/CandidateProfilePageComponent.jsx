var Grid = ReactBootstrap.Grid;
var Row = ReactBootstrap.Row;
var Col = ReactBootstrap.Col;
var Image = ReactBootstrap.Image;
var Well = ReactBootstrap.Well;

var CandidateProfilePageComponent = React.createClass({
    render: function() {
        return (
            <Grid>
                <Row className="show-grid">
                    <Col sm={7} smPush={5} xsHidden>
                        <Image src="https://www.ibm.com/developerworks/community/profiles/photo.do?userid=120000CA5U" circle/>
                    </Col>
                </Row>
                <Row className="show-grid">
                    <Col xs={12}>
                        <div className="title">
                            {this.props.candidate.firstName}&nbsp;{this.props.candidate.lastName}
                        </div>
                    </Col>
                </Row>

                <Row className="show-grid">
                    <Col xs={12} md={12}>
                        <Well>
                            <div>
                                <p>
                                    <strong>Vardas:&nbsp;</strong>
                                    {this.props.candidate.firstName}</p><hr/>
                                <p>
                                    <strong>Pavardė:&nbsp;</strong>
                                    {this.props.candidate.lastName}</p><hr/>
                                <p>
                                    <strong>Gimimo data:&nbsp;</strong>
                                    {this.props.candidate.date}</p><hr/>
                                <p>
                                    <strong>Partinė priklausomybė:&nbsp;</strong>
                                    {this.props.partyName}</p><hr/>
                                <p>
                                    <strong>Aprašymas:&nbsp;</strong>
                                    {this.props.candidate.description}</p>
                            </div>
                        </Well>
                    </Col>
                </Row>
            </Grid>
        );
    }
});

window.CandidateProfilePageComponent = CandidateProfilePageComponent;
