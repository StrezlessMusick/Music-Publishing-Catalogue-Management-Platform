import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {ErrorPageComponent} from "./error-page/error-page.component";
import {ArtistsComponent} from "./artists/artists.component";
import {ProjectsComponent} from "./projects/projects.component";
import {TracksComponent} from "./tracks/tracks.component";

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'artists', component: ArtistsComponent},
  {path: 'projects', component: ProjectsComponent},
  {path: 'tracks', component: TracksComponent},


  // fallback error pages
  {
    path: 'not-found',
    component: ErrorPageComponent,
    data: {message: 'Page not found!'}
  },
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
