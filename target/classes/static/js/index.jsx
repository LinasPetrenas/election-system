var Link = ReactRouter.Link;

var App = React.createClass( {
    render: function() {
        return (
            <div>
                <NavigationBarComponent />
                <div className="container-fluid">
                    {this.props.children}
                </div>
                <FooterBarComponent />
            </div>

        );
    }
});

var Router = ReactRouter.Router;
var Route = ReactRouter.Route;
var IndexRoute = ReactRouter.IndexRoute;
var hashHistory = ReactRouter.hashHistory;

ReactDOM.render((
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={HomePageContainer} />
            <Route path="/hello-world" component={HelloWorldComponent} />
            <Route path="/county-list" component={CountyListContainer} />
            <Route path="/search-result" component={CandidateSearchResultContainer} />
            <Route path="/candidate-profile/:personCode" component={CandidateProfilePageContainer} />
            <Route path="/admin" component={AdminPageContainer} />
            <Route path="/county/districts/:id" component={DistrictPageContainer} />
            <Route path="/representative/:id" component={RepresentativePageContainer} />
            <Route path="/add-representative/county/:countyId/:id" component={AddRepresentativeContainer} />
            <Route path="/singleMember/main-result" component={SingleMemberMainResultContainer} />
            <Route path="/singleMember/county-result/:id" component={SingleMemberCountyResultContainer} />
            <Route path="/singleMember/district-result/:countyId/:id" component={SingleMemberDistrictResultContainer} />
            <Route path="/multiMember/main-result" component={MultiMemberMainResultContainer} />
            <Route path="/multiMember/county-result/:id" component={MultiMemberCountyResultContainer} />
            <Route path="/multiMember/district-result/:countyId/:id" component={MultiMemberDistrictResultContainer} />
            
            <Route path="/countyDistrictList/:id" component={CountyDistrictListContainer} />
			 <Route path="/finalResultSingleMemberElected" component={FinalResultSingleMemberElectedComponent} />
			  <Route path="/finalResultMultiMemberElected" component={FinalResultMultiMemberElectedComponent} />
			   <Route path="/finalResultParlamentMemberElected" component={FinalResultParlamentMemberElectedComponent} />
			    <Route path="/finalResultPartyElected" component={FinalResultPartyElectedComponent} />




            <Route path="/login" component={LoginContainer} />
            <Route path="*" component={NoMatch} />
        </Route>
    </Router>
), document.getElementById( 'root' ) );
