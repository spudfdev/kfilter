# Minecraft Chat Filter Plugin

A customizable Minecraft plugin designed to filter and manage chat messages on your server. This plugin allows server admins to block specific words, replace them with custom text, and manage the filter directly in-game.

---

## Features

- **Configurable Word Filter**: Block specific words from being used in chat.
- **Custom Replacements**: Replace blocked words with custom text (e.g., "****" or a funny phrase).
- **Clear Chat Command**: Clear the chat for all players with a simple command.
- **In-Game Management**:
    - Add new words to the filter.
    - Remove words from the filter.
    - List all currently blocked words.
- **Easy Configuration**: Simple configuration via `config.yml`.

---


## Commands

| Command                  | Description                                   | Permission     |
|--------------------------|-----------------------------------------------|----------------|
| `/kfilter add <word>`    | Add a word to the filter.                     | `kf.add`       |
| `/kfilter remove <word>` | Remove a word from the filter.              | `kf.remove`    |
| `/kfilter list`          | List all blocked words.                       | `kf.list`      |
| `/kfilter clearchat`     | Clear the chat for all players.               | `kf.clearchat` |

---

## Configuration

The `config.yml` file allows you to customize the list directly. Here’s an example configuration:

```yaml
# List of blocked words
blocked-words:
  badword1: replacement1
  badword2: replacement2