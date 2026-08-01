# Transcription categorization project — Desktop

This is the desktop variant of original fullstack transcript-categorization project that can be found [there](https://github.com/Herob527/categorize-files).

I've decided that I want to learn Jetpack Compose to compare it to Flutter, so I've decided to create offline desktop variant of it with some additions / changes that will be covered in scope.

At first, I'm going to make it local-only that integration with backend would be fully optional thing.

## Current scope

### Targets

- Windows (.msi)
- Linux (.appgimage)

Not owning MacOS so I cannot reliably test it

### Projects view

Projects will be stored in folders on disk.

- [] Create new project
- [] Pick folder to store project data
- [] Display recent projects
- [] Quick summary of project (directory, counts of audios and categories , current progress of transcription and categorization)

#### Options

Each project item will have configuration view (big dialog or separate route) with following options:

- [] Manage categories (remove, rename)
- [] Remove project
- [] Rename project
- [] Add audio to / remove item from project

### Transcript view

Each transcript item will have capability to attach category, update transcription, play audio or removing entry.

- [] Write / clear transcript
- [] Play / pause audio
- [] Sort items by audio length or category name
- [] Paginate items with option to change page size
- [] Remove entry (with modal)
- [] Auto-save changes into SQLite DB

#### Category item

- [] Attach / detach category to audio / transcript
- [] Add category to project
- [] Remove category from project (cascade removal with confirm modal)

#### Audio item

- [] Play / pause audio
- [] If possible, use waveform instead of mundane audio player
- [] Add keyboard shortcut to play audio
- [] Replace audio

### Export view

Export view will enable user to some degree of customization of output for later processing

- [] Create formatted transcript, so user can pick, what properties should be includes in lines (category index / name, audio length, text, audio name)
- [] Add capability for user to pick if everything should be in one folder or separated by categories (optionally: audios shall be in wavs and transcript in root)
- [] Add capability to pick if transcript should be created in the first place
- [] Add option to archive (by default it'll create output)
- [] Notify user about export status
- [] To check if user could still edit when exporting is going on (to avoid data corruption)

## Ideas (non goals)

- [] Metadata in export (requires JSON creation pipeline)
- [] Integration with backend from [there](https://github.com/Herob527/categorize-files).
It'd require project, auth support host configuration and deployment readiness
- [] Auth support
- [] Offline and online project separation
- [] Whisper host integration
