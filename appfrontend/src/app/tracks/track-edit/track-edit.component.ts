import {Component, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {tap} from "rxjs/operators";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {TracksService} from "../../zshared/services/tracks.service";
import {Track} from "../../zshared/interfaces/track";
import {Artist} from "../../zshared/interfaces/artist";
import {ArtistsService} from "../../zshared/services/artists.service";
import {PRO} from "../../zshared/enums/pro";

@Component({
  selector: 'app-track-edit',
  templateUrl: './track-edit.component.html',
  styleUrls: ['./track-edit.component.css']
})
export class TrackEditComponent implements OnInit {
  track: Track;
  id: number;
  editMode = false;
  trackForm: FormGroup;
  artists: Artist[];
  artist: Artist;
  pro = PRO;
  proKeys = [];

  constructor(private tracksService: TracksService,
              private artistsService: ArtistsService,
              private route: ActivatedRoute,
              private router: Router) {
    this.proKeys = Object.keys(this.pro);
    this.onGetArtists();
    this.initForm();
  }

  ngOnInit(): void {
    this.route.params
      .subscribe(param => {
          this.id = +param['id'];
          this.editMode = param['id'] != null;
          this.initForm();
        });

  }

  onSubmit() {
    if (this.editMode) {
      this.tracksService.updateTrack(this.trackForm.value)
        .pipe(tap(track => {
          this.track = track;
        })).subscribe();
    } else {
      this.tracksService.addTrack(this.trackForm.value)
        .pipe(tap(newTrack => {
          this.track = newTrack
        })).subscribe();
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }

  private async initForm() {
    let trackId: number;
    let trackName = '';
    let trackImageUrl = '';
    let trackUrl = '';
    let trackLength = 0;

    let trackArtist = new FormArray([]);

    if (this.editMode) {
      const track = await this.tracksService
        .getTrack(this.id).toPromise();

      trackId = track?.id;
      trackName = track?.trackName;
      trackImageUrl = track?.trackImageUrl;
      trackUrl = track?.trackUrl;
      trackLength = track?.trackLength;

      if (track['artist']) {
        for (let artist of track.artist) {
          trackArtist.push(
            new FormGroup({
              artistName: new FormControl(artist.artistName),
              artistImageUrl: new FormControl(artist.artistImageUrl),
              pro: new FormControl(artist.pro),
              proIPI: new FormControl(artist.proIPI)
            })
          );
        }
      }

    }

    this.trackForm = new FormGroup({
      id: new FormControl(trackId),
      trackName: new FormControl(trackName),
      trackImageUrl: new FormControl(trackImageUrl),
      trackUrl: new FormControl(trackUrl),
      trackLength: new FormControl(trackLength),
      'artist': trackArtist
    });
  }

  get artistControls() {
    return (<FormArray>this.trackForm.get('artist')).controls;
  }

  onGetArtists() {
    this.artistsService.getArtists()
      .pipe(tap(
        artists => this.artists = artists
      )).subscribe();
  }

  onAddArtist() {
    (<FormArray>this.trackForm.get('artist')).push(
      new FormGroup({
        artistName: new FormControl(null),
        artistImageUrl: new FormControl(null),
        pro: new FormControl(null),
        proIPI: new FormControl(null)
      })
    );
  }

  onNewArtist() {
    // TODO: Creates non-existent artist when creating new track or updating existing track
  }

  onDeleteArtist(index: number) {
    (<FormArray>this.trackForm.get('artist')).removeAt(index);
  }
}
