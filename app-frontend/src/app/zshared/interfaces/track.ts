import {Artist} from "./artist";
import {Project} from "./project";

export interface Track {
  id: number;
  trackName: string;
  trackImageUrl: string;
  trackLength: string;
  artist: Artist[];
  project: Project[];
}
