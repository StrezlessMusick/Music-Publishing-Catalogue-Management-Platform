import {Track} from "./track";
import {Artist} from "./artist";

export interface Project {
  id: number;
  projectName: string;
  projectImageUrl: string;
  numOfTracks: number;
  projectLength: number;
  artist: Artist[];
  trackList: Track[];
}
