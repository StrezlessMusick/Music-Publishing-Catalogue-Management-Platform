import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {ArtistsComponent} from './artists/artists.component';
import {TracksComponent} from './tracks/tracks.component';
import {ProjectsComponent} from './projects/projects.component';
import {AppRoutingModule} from "./app-routing.module";
import { HeaderComponent } from './header/header.component';
import { ArtistDetailsComponent } from './artists/artist-details/artist-details.component';
import { ArtistListComponent } from './artists/artist-list/artist-list.component';
import { ProjectDetailsComponent } from './projects/project-details/project-details.component';
import { ProjectListComponent } from './projects/project-list/project-list.component';
import { TrackDetailsComponent } from './tracks/track-details/track-details.component';
import { TrackListComponent } from './tracks/track-list/track-list.component';
import { HomeComponent } from './home/home.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { ArtistListEntryComponent } from './artists/artist-list/artist-list-entry/artist-list-entry.component';
import { ArtistEditComponent } from './artists/artist-edit/artist-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    ArtistsComponent,
    TracksComponent,
    ProjectsComponent,
    HeaderComponent,
    ArtistDetailsComponent,
    ArtistListComponent,
    ProjectDetailsComponent,
    ProjectListComponent,
    TrackDetailsComponent,
    TrackListComponent,
    HomeComponent,
    ErrorPageComponent,
    ArtistListEntryComponent,
    ArtistEditComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
