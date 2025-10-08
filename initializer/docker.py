import os
import platform
import subprocess
import time

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

services = {
    "posts": os.path.abspath(os.path.join(BASE_DIR, "..", "posts")),
    "profiles": os.path.abspath(os.path.join(BASE_DIR, "..", "profiles")),
    "users": os.path.abspath(os.path.join(BASE_DIR, "..", "users")),
    "catalog": os.path.abspath(os.path.join(BASE_DIR, "..", "catalog")),
    "pages": os.path.abspath(os.path.join(BASE_DIR, "..", "pages")),
}

delay_seconds = 15

def open_terminal_and_docker_compose(name, path):
    system = platform.system()

    cmd = "docker compose up"

    if system == "Windows":
        subprocess.Popen(f'start cmd /K "cd {path} && {cmd}"', shell=True)
    elif system == "Linux":
        try:
            subprocess.Popen(
                ["gnome-terminal", "--", "bash", "-c", f"cd {path} && {cmd}; exec bash"]
            )
        except FileNotFoundError:
            subprocess.Popen(cmd, cwd=path, shell=True)
    else:
        raise RuntimeError(f"Sistema operativo no soportado: {system}")


def main():
    for svc in ["posts", "profiles", "users", "catalog", "pages"]:
        print(f"Iniciando docker compose para {svc}...")
        open_terminal_and_docker_compose(svc, services[svc])
        time.sleep(delay_seconds)


if __name__ == "__main__":
    main()
