import {Track} from "./track";
import {Project} from "./project";

export interface Artist {
  id: number;
  artistName: string;
  artistImageUrl: string;
  pro: string;
  proIPI: string;
  artistTrack: Track[];
  artistProject: Project[];
}
