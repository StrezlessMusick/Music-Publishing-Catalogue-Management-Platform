import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {ErrorPageComponent} from "./error-page/error-page.component";
import {ArtistsComponent} from "./artists/artists.component";
import {ProjectsComponent} from "./projects/projects.component";
import {TracksComponent} from "./tracks/tracks.component";
import {ArtistEditComponent} from "./artists/artist-edit/artist-edit.component";
import {ArtistDetailsComponent} from "./artists/artist-details/artist-details.component";
import {ProjectEditComponent} from "./projects/project-edit/project-edit.component";
import {ProjectDetailsComponent} from "./projects/project-details/project-details.component";
import {TrackEditComponent} from "./tracks/track-edit/track-edit.component";
import {TrackDetailsComponent} from "./tracks/track-details/track-details.component";

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {
    path: 'artists', component: ArtistsComponent,
    children: [
      {path: 'new', component: ArtistEditComponent},
      {
        path: 'id',
        component: ArtistDetailsComponent
      },
      {
        path: 'id/edit',
        component: ArtistEditComponent
      }
    ]
  },
  {
    path: 'projects', component: ProjectsComponent,
    children: [
      {path: 'new', component: ProjectEditComponent},
      {
        path: 'id',
        component: ProjectDetailsComponent
      },
      {
        path: 'id/edit',
        component: ProjectEditComponent
      }
    ]
  },
  {
    path: 'tracks', component: TracksComponent,
    children: [
      {path: 'new', component: TrackEditComponent},
      {
        path: 'id',
        component: TrackDetailsComponent
      },
      {
        path: 'id/edit',
        component: TrackEditComponent
      }
    ]
  },


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
