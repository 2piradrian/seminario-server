import os
import platform
import subprocess
import time

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

services = {
    "common": os.path.abspath(os.path.join(BASE_DIR, "..", "common")),
    "registry": os.path.abspath(os.path.join(BASE_DIR, "..", "registry")),
    "config": os.path.abspath(os.path.join(BASE_DIR, "..", "config")),
    "gateway": os.path.abspath(os.path.join(BASE_DIR, "..", "gateway")),
    "posts": os.path.abspath(os.path.join(BASE_DIR, "..", "posts")),
    "results": os.path.abspath(os.path.join(BASE_DIR, "..", "results")),
    "users": os.path.abspath(os.path.join(BASE_DIR, "..", "users")),
    "catalog": os.path.abspath(os.path.join(BASE_DIR, "..", "catalog")),
    "images": os.path.abspath(os.path.join(BASE_DIR, "..", "images")),
    "page-profiles": os.path.abspath(os.path.join(BASE_DIR, "..", "page-profiles")),
    "events": os.path.abspath(os.path.join(BASE_DIR, "..", "events")),
    "notifications": os.path.abspath(os.path.join(BASE_DIR, "..", "notifications"))
}

delay_seconds = 15

def run_maven_install():
    commons_path = services["common"]
    subprocess.run("mvn package install", cwd=commons_path, check=True, shell=True)


def open_terminal_and_run(name, path):
    system = platform.system()

    if system == "Windows":
        subprocess.Popen(f'start cmd /K "cd {path} && mvn spring-boot:run"', shell=True)
    elif system == "Linux":
        try:
            subprocess.Popen(["gnome-terminal", "--", "bash", "-c", f"cd {path} && mvn spring-boot:run; exec bash"])
        except FileNotFoundError:
            subprocess.Popen(["mvn", "spring-boot:run"], cwd=path)
    else:
        raise RuntimeError(f"Sistema operativo no soportado: {system}")


def main():
    run_maven_install()
    for svc in ["registry", "config", "gateway", "posts", "results", "users", "catalog", "images", "page-profiles", "events", "notifications"]:
        open_terminal_and_run(svc, services[svc])

        if svc == "registry" or svc == "config":
            time.sleep(delay_seconds)


if __name__ == "__main__":
    main()
