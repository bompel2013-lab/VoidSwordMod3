# Void Sword Mod (Forge 1.20.1)

Craft a **Void Sword** by combining a **Netherite Sword** and a **Dragon's Breath** (shapeless recipe).
While holding the Void Sword, **Shift + Right-Click** to **erase the entire chunk** you are currently in.

> ⚠️ WARNING: This permanently clears blocks in that chunk on your world. Back up your saves!

## Build
1. Install JDK 17.
2. Unzip this project.
3. In the project folder, run:
   - Windows: `gradlew.bat build`
   - macOS/Linux: `./gradlew build`
4. Your mod jar will be at: `build/libs/voidsword-1.20.1-1.0.0.jar`

## Dev Run
- `./gradlew runClient` to test in a dev environment.

## Notes
- Cooldown: 10s per use, consumes 1 durability each erase.
- The sword is netherite-tier and fire-resistant, with Netherite-like durability (2031).
- The item reuses the Netherite Sword texture by default to keep the project minimal.
